package view;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

/**
 * Main GUI class that holds the main components for the View.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name MainView.java
 */
public class MainView extends SplitPane {

	private TimelineView timeline;
	private MenuView menu;

	public MainView() {
		menu = new MenuView();
		timeline = new TimelineView();
		super.setOrientation(Orientation.VERTICAL);
		super.getItems().addAll(menu, timeline);
	}

	public MenuView getMenuView() {
		return menu;
	}
	
	public TimelineView getTimelineView() {
		return timeline;
	}
}
