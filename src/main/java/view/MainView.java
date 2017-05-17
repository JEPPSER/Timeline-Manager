package view;

import de.jensd.fx.fontawesome.AwesomeStyle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

	public MainView(Stage stage) {
		menu = new MenuView();
		timeline = new TimelineView();
		super.setOrientation(Orientation.VERTICAL);
		super.getItems().addAll(menu, timeline);
		
		setListeners(stage);
	}

	private void setListeners(Stage stage) {
		
		menu.getThemesButton().setOnAction(e -> {
			if (!stage.getScene().getStylesheets().isEmpty()) {
				stage.getScene().getStylesheets().clear();
				timeline.setTextColor(Color.BLACK);
			} else {
				stage.getScene().getStylesheets().add(AwesomeStyle.DARK.getStylePath());
				timeline.setTextColor(Color.WHITE);
			}
			timeline.setTimeline(timeline.getTimeline(), timeline.getTimePerspective());
		});

		menu.getToggleGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {

				((Node) toggle).setDisable(false);
				((Node) new_toggle).setDisable(true);

				if (new_toggle.toString().contains("Week")) {
					timeline.setTimeline(timeline.getTimeline(), "Week");
				} else if (new_toggle.toString().contains("Month")) {
					timeline.setTimeline(timeline.getTimeline(), "Month");
				} else if (new_toggle.toString().contains("Year")) {
					timeline.setTimeline(timeline.getTimeline(), "Year");
				}
			}
		});
	}

	public MenuView getMenuView() {
		return menu;
	}

	public TimelineView getTimelineView() {
		return timeline;
	}
}
