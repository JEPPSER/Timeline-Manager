package controller;

import java.time.LocalDate;

import interfaces.TimelinePopupListener;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Timeline;
import model.TimelineContainer;

/**
 * Controller that handles all actions made by the user in the TimelinePopup.
 * The purpose of the TimelinePopup is to create a timeline.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class TimelinePopupController implements TimelinePopupListener {

	private TimelineContainer timelineContainer;

	public TimelinePopupController(TimelineContainer tc) {
		timelineContainer = tc;
	}

	@Override
	public void onSaveButtonClicked(String title, LocalDate startDate, LocalDate endDate, Stage stage) {

		if (title.equals("")) {
			MainController.showAlert(AlertType.ERROR, "Title must not be empty", ButtonType.OK);
		} else if (startDate == null || endDate == null) {
			MainController.showAlert(AlertType.ERROR, "Dates have not been set", ButtonType.OK);
		} else if (startDate.isAfter(endDate)) {
			MainController.showAlert(AlertType.ERROR, "End Date can not be before Start Date", ButtonType.OK);
		} else {
			int year1 = Integer.parseInt(startDate.toString().substring(0, 4));
			int year2 = Integer.parseInt(endDate.toString().substring(0, 4));
			if (year2 - year1 > 50) {
				MainController.showAlert(AlertType.ERROR, "Duration of a Timeline must not exceed 50 years", ButtonType.OK);
			} else {
				Timeline timeline = new Timeline();
				timeline.setEndDate(endDate);
				timeline.setStartDate(startDate);
				timeline.setName(title);
				timeline.setHasUnsavedChanges(true);
				timelineContainer.addTimeline(timeline);
				stage.close();
			}
		}
	}
}
