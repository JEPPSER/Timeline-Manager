package controller;

import java.util.List;

import interfaces.ModelChangedListener;
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
		timelineViewController = new TimelineViewController();
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
		mainView.getTimelineView().setTimeline(active);
		// TODO: Tell the view to update
	}
}
