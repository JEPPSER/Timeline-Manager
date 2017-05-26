package controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import de.jensd.fx.fontawesome.AwesomeStyle;
import interfaces.ModelChangedListener;
import io.FileHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import main.TimelineManager;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import model.Timeline;
import model.TimelineContainer;
import view.MainView;

/**
 * The MainController creates all other controller classes and sets up their communication
 * with the view classes. It also implements the interface ModelChangedListener so it can
 * be notified when the timelines in TimelineContainer changes, for example when a timeline
 * gets added.
 * 
 * @author Daniel Alm Grundstrom
 * @version 0.00.00
 * @name MainController.java
 */
public class MainController implements ModelChangedListener {
	private static Window window;
	
	private MainView mainView;
	private TimelineContainer timelineContainer;
	private FileHandler fileHandler;
	private MenuController menuController;
	private TimelineViewController timelineViewController;
	
	/**
	 * Constructor. Creates the controllers and stores references to MainView and
	 * TimelineContainer for later use.
	 * 
	 * @param mainView - reference to MainView
	 * @param timelineContainer - reference to TimelineContainer
	 */
	public MainController(MainView mainView, TimelineContainer timelineContainer) {
		this.mainView = mainView;
		this.timelineContainer = timelineContainer;
		this.fileHandler = new FileHandler();
		menuController = new MenuController(timelineContainer, mainView.getMenuView(), mainView.getTimelineView());
		timelineViewController = new TimelineViewController(timelineContainer);
	}
	
	@Override
	public void onModelChanged(List<Timeline> timelines, Timeline active) {
		System.out.println("MainController: TimelineContainer has been updated");
		mainView.getTimelineView().setTimeline(active, "");
		mainView.getMenuView().updateTimelineDropdown(timelines, active);
	}
	
	/**
	 * Called when application starts
	 */
	public void onStart() {
		window = mainView.getScene().getWindow();
		setupListeners();
		loadConfig();
	}
	
	/**
	 * Called when the main window is closed. Checks if any timelines are unsaved and in that case 
	 * asks the user if he/she wants to save the unsaved timelines, close the program without saving or
	 * cancel and not exit the program.
	 * 
	 * @param e - the window event that triggered closing of the window
	 */
	public void onExit(WindowEvent e) {
		
		List<Timeline> unsavedTimelines = timelineContainer.getTimelines().stream().filter(t -> t.getHasUnsavedChanges()).collect(Collectors.toList());
		
		if (unsavedTimelines.size() > 0) {
			ButtonType result = MainController.showAlert(AlertType.CONFIRMATION, "There are unsaved timelines. Do you want to save them before closing? ",
					ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			
			if (result == ButtonType.YES) {
				
				for (Timeline t : unsavedTimelines) {
					
					if (t != timelineContainer.getActiveTimeline()) {
						timelineContainer.setActiveTimeline(t);
					}
					
					menuController.onSaveButtonClicked();
				}
				
				if (timelineContainer.getTimelines().stream().filter(t -> t.getHasUnsavedChanges()).count() > 0) {
					MainController.showAlert(AlertType.ERROR, "One or more timelines could not be saved. Try again before exiting.", ButtonType.OK);
					e.consume();
				} else {
					saveConfig();
					TimelineManager.exit();
				}
			} else if (result == ButtonType.NO) {
				saveConfig();
				TimelineManager.exit();
			} else {
				e.consume();
			}
		} else {
			saveConfig();
			TimelineManager.exit();
		}
	}
	
	/**
	 * Show an alert window.
	 * 
	 * @param type - alert window type. Can be one of: CONFIRMATION, ERROR, INFORMATION, NONE or WARNING
	 * @param message - message to display
	 * @param buttons - buttons to include in the window. Can be any combination of: APPLY, CANCEL, CLOSE, FINISH, NEXT, NO, OK, PREVIOUS and YES
	 * 
	 * @return the ButtonType of the button the user clicked, or 'null' if the user closes the window in another way
	 */
	static ButtonType showAlert(AlertType type, String message, ButtonType... buttons) {
		Alert alert = new Alert(type, message, buttons);
		
		alert.initOwner(window);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
		ButtonType result = alert.getResult();
		alert.close();
		
		return result;
	}
	
	private void setupListeners() {
		timelineContainer.registerListener(this);
		mainView.getMenuView().registerListener(menuController);
		mainView.getTimelineView().registerListener(timelineViewController);
	}
	
	private void loadConfig() {
		String theme = "";
		
		try {
			theme = fileHandler.readProperty("theme", "light");
		} catch (Exception ex) {
			System.err.println("Could not read config file. Message: " + ex.getMessage());
		}
		
		if (theme.toLowerCase().equals("light")) {
			mainView.getTimelineView().setTextColor(Color.BLACK);
		} else if (theme.toLowerCase().equals("dark")) {
			mainView.getScene().getStylesheets().add(AwesomeStyle.DARK.getStylePath());
			mainView.getTimelineView().setTextColor(Color.WHITE);
		}
	}
	
	private void saveConfig() {
		try {
			if (mainView.getScene().getStylesheets().size() > 0) {
				fileHandler.writeProperty("theme", "dark");
			} else {
				fileHandler.writeProperty("theme", "light");
			}
		} catch (IOException ex) {
			System.err.println("Could not write config file. Message: " + ex.getMessage());
		}
	}

}
