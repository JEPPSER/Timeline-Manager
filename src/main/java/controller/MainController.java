package controller;

import java.util.List;
import java.util.stream.Collectors;

import interfaces.ModelChangedListener;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import main.TimelineManager;
import javafx.scene.control.Alert.AlertType;
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
		menuController = new MenuController(timelineContainer, mainView.getMenuView());
		timelineViewController = new TimelineViewController(timelineContainer);
	}
	
	/**
	 * Sets up the communication between the controller classes and the view classes, 
	 * as well as communication between this controller (MainController) and TimelineContainer.
	 */
	public void setupListeners() {
		timelineContainer.registerListener(this);
		mainView.getMenuView().registerListener(menuController);
		mainView.getTimelineView().registerListener(timelineViewController);
		window = mainView.getScene().getWindow();
	}

	@Override
	public void onModelChanged(List<Timeline> timelines, Timeline active) {
		System.out.println("MainController: TimelineContainer has been updated");
		mainView.getTimelineView().setTimeline(active, "");
		mainView.getMenuView().updateTimelineDropdown(timelines, active);
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
					
					menuController.onSaveButtonClicked((Stage)mainView.getScene().getWindow());
				}
				
				if (timelineContainer.getTimelines().stream().filter(t -> t.getHasUnsavedChanges()).count() > 0) {
					MainController.showAlert(AlertType.ERROR, "One or more timelines could not be saved. Try again before exiting.", ButtonType.OK);
					e.consume();
				} else {
					TimelineManager.exit();
				}
			} else if (result == ButtonType.NO) {
				TimelineManager.exit();
			} else {
				e.consume();
			}
		} else {
			TimelineManager.exit();
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param message
	 * @param buttons
	 * @return
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
}
