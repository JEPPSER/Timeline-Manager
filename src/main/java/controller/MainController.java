package controller;

import interfaces.ModelChangedListener;
import model.TimelineContainer;
import view.MainView;

public class MainController implements ModelChangedListener {

	private MainView mainView;
	private TimelineContainer timelineContainer;
	private MenuController menuController;
	private TimelineViewController timelineViewController;
	
	public MainController(MainView mainView, TimelineContainer timelineContainer) {
		this.mainView = mainView;
		this.timelineContainer = timelineContainer;
		menuController = new MenuController(timelineContainer);
		timelineViewController = new TimelineViewController();
	}
	
	public void setupListeners() {
		timelineContainer.registerListener(this);
		mainView.getMenuView().registerListener(menuController);
		mainView.getTimelineView().registerListener(timelineViewController);
	}

	@Override
	public void onModelChanged() {
		System.out.println("MainController: TimelineContainer has been updated");
	}
}
