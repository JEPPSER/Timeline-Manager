package controller;

import java.time.LocalDate;

import interfaces.TimelinePopupListener;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.Timeline;
import view.DateNullWarning;
import view.DateWarning;
import view.MenuView;
import view.TitleWarning;

/**
 * Controller that handles all actions made by the user in the TimelinePopup.
 * The purpose of the TimelinePopup is to create a timeline.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class TimelinePopupController implements TimelinePopupListener {
	
	MenuItem item;
	
	@Override
	public void onSaveButtonClicked(String title, LocalDate startDate, LocalDate endDate, Stage stage, MenuController controller, MenuView menu) {

		if (title.equals("")) {
			TitleWarning warning = new TitleWarning();
			warning.close();
		} else if(startDate == null || endDate == null){
			DateNullWarning warning = new DateNullWarning();
			warning.close();
		} else if (startDate.isAfter(endDate)) {
			DateWarning warning = new DateWarning();
			warning.close();
		} else {
			Timeline timeline = new Timeline();
			timeline.setEndDate(endDate);
			timeline.setStartDate(startDate);
			timeline.setName(title);
			controller.getTimelineContainer().addTimeline(timeline);
			item = new MenuItem();
			item.setText(title);
			menu.getLoadedTimelines().getItems().add(item);
			menu.getLoadedTimelines().setText(title);
			controller.getTimelineContainer().setActiveTimeline(timeline);
			stage.close();
			
			item.setOnAction(e -> {
				controller.getTimelineContainer().setActiveTimeline(timeline);
				menu.getLoadedTimelines().setText(title);
			});
		}
	}
}
