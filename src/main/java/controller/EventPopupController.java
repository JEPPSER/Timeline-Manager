package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import interfaces.EventPopupListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import model.Event.EventType;
import model.Timeline;
import model.TimelineContainer;
import javafx.scene.control.Alert.AlertType;
import view.EventPopup;

/**
 * Controller that handles all actions made by the user in the EventPopup.
 * The purpose of the EventPopup is to allow the user to input information about an event to be added
 * or edit information about an event.
 * 
 * @author Daniel Alm Grundstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class EventPopupController implements EventPopupListener {

	private EventPopup eventPopup;
	private TimelineContainer container;
	
	public EventPopupController(EventPopup popup, TimelineContainer tc) {
		eventPopup = popup;
		container = tc;
	}
	
	@Override
	public void onOkayButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
		    						LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		
		boolean isDurationEvent = isDurationEvent(eventTypeToggle);
		Timeline currentTimeline = container.getActiveTimeline();
		
		if (eventTitle.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR, "Title must not be empty", ButtonType.OK);
			alert.showAndWait();
		} else if (eventTypeToggle == null) {
			Alert alert = new Alert(AlertType.ERROR, "An event type must be selected.", ButtonType.OK);
			alert.showAndWait();
		} else if (isDurationEvent && (startDate == null || startTime == null || endDate == null || endTime == null)) {	// valid duration dates and time?
			Alert alert = new Alert(AlertType.ERROR, "Date and time have not been set", ButtonType.OK);
			alert.showAndWait();
		} else if (!isDurationEvent && (startDate == null || startTime == null)) {	// valid non-duration dates and time?	
			Alert alert = new Alert(AlertType.ERROR, "Date and time have not been set", ButtonType.OK);
			alert.showAndWait();
		} else if (isDurationEvent && LocalDateTime.of(startDate, startTime).isAfter(LocalDateTime.of(endDate, endTime))) {
			Alert alert = new Alert(AlertType.ERROR, "End Date can not be before Start Date", ButtonType.OK);
			alert.showAndWait();
		} else if (!isDatesWithinTimeline(startDate, endDate, currentTimeline)) {
			Alert alert = new Alert(AlertType.ERROR, String.format("Event date(s) must be within range %s - %s",
					currentTimeline.getStartDate(), currentTimeline.getEndDate()), ButtonType.OK);
			alert.showAndWait();
		} else {
			EventType type = isDurationEvent ? EventType.DURATION : EventType.NON_DURATION;
			container.addEvent(eventTitle, eventDescription, startDate, endDate, type);
			eventPopup.close();
		}
		
	}
	
	private boolean isDurationEvent(Toggle toggle) {
		return toggle != null && toggle.getUserData().toString().equals("duration");
	}
	
	private boolean isDatesWithinTimeline(LocalDate eventStart, LocalDate eventEnd, Timeline timeline) {
		if (eventEnd == null) { // non-duration event?
			return eventStart.minusDays(1).isBefore(timeline.getEndDate()) 
					&& eventStart.plusDays(1).isAfter(timeline.getStartDate());
		} else {
			return eventStart.minusDays(1).isBefore(timeline.getEndDate()) 
					&& eventStart.plusDays(1).isAfter(timeline.getStartDate())
					&& eventEnd.plusDays(1).isAfter(timeline.getStartDate()) 
					&& eventEnd.minusDays(1).isBefore(timeline.getEndDate());
		}
	}

}