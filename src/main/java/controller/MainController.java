package controller;

import java.util.List;
import java.util.stream.Collectors;

import interfaces.ModelChangedListener;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
			Alert alert = new Alert(AlertType.CONFIRMATION, "There are unsaved timelines. Do you want to save them before closing? ",
					ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.setHeaderText(null);
			alert.showAndWait();
			
			ButtonType result = alert.getResult();
			
			if (result == ButtonType.YES) {
				
				for (Timeline t : unsavedTimelines) {
					mainView.getTimelineView().setTimeline(t, "");
					menuController.onSaveButtonClicked((Stage)mainView.getScene().getWindow());
				}
				
				Platform.exit();
			} else if (result == ButtonType.NO) {
				Platform.exit();
			} else {
				e.consume();
			}
		}
	}
}
