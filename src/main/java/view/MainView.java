package view;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

/**
 * Main GUI class that holds the main components for the View.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name MainView.java
 */
public class MainView extends SplitPane {

	private TimelineView timeline = new TimelineView();
	private MenuView menu = new MenuView();

	public MainView() {
		super.setOrientation(Orientation.VERTICAL);
		super.getItems().addAll(menu, timeline);
	}

	public Button getAddTimelineButton() {
		return menu.getAddTimelineButton();
	}

	public Button getDeleteTimelineButton() {
		return menu.getDeleteTimelineButton();
	}
}
