package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import interfaces.EventPopupListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.control.Alert.AlertType;
import view.EventPopup;

/**
 * Controller that handles all actions made by the user in the EventPopup.
 * The purpose of the EventPopup is to allow the user to input information about an event to be added
 * or edit information about an event.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class EventPopupController implements EventPopupListener {

	private EventPopup eventPopup;
	
	public EventPopupController(EventPopup popup) {
		eventPopup = popup;
	}
	
	@Override
	public void onOkayButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
		    						LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		
		boolean isDurationEvent = isDurationEvent(eventTypeToggle);
		
		if (eventTitle.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Title must not be empty", ButtonType.OK);
			alert.showAndWait();
		} else if (eventTypeToggle == null) {
			Alert alert = new Alert(AlertType.ERROR, "An event type must be selected.", ButtonType.OK);
			alert.showAndWait();
		} else if (isDurationEvent && (startDate == null || startTime == null || endDate == null || endTime == null)) {	// valid duration dates and time?
			System.out.println("duration");
			Alert alert = new Alert(AlertType.ERROR, "Date and time have not been set", ButtonType.OK);
			alert.showAndWait();
		} else if (!isDurationEvent && (startDate == null || startTime == null)) {	// valid non-duration dates and time?	
			System.out.println("not duration");
			Alert alert = new Alert(AlertType.ERROR, "Date and time have not been set", ButtonType.OK);
			alert.showAndWait();
		} else if (isDurationEvent && LocalDateTime.of(startDate, startTime).isAfter(LocalDateTime.of(endDate, endTime))) {
			Alert alert = new Alert(AlertType.ERROR, "End Date can not be before Start Date", ButtonType.OK);
			alert.showAndWait();
		} else {
			// TODO: Create event
			eventPopup.close();
		}
		
	}
	
	private boolean isDurationEvent(Toggle toggle) {
		return toggle != null && toggle.getUserData().toString().equals("duration");
	}

}