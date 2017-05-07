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
	public void onAddButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
		    						LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		
		if (checkInput(eventTypeToggle, eventTitle, eventDescription, startDate, startTime, endDate, endTime)) {
			EventType type = isDurationEvent(eventTypeToggle) ? EventType.DURATION : EventType.NON_DURATION;
			container.addEvent(eventTitle, eventDescription, startDate, endDate, type);
			eventPopup.close();
		}
	}
	
	@Override
	public void onEditButtonClicked(int eventId, Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
		    						LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		
		if (checkInput(eventTypeToggle, eventTitle, eventDescription, startDate, startTime, endDate, endTime)) {
			EventType newType = isDurationEvent(eventTypeToggle) ? EventType.DURATION : EventType.NON_DURATION;
			container.editEvent(eventId, eventTitle, eventDescription, startDate, endDate, newType);
			eventPopup.close();
		}
		
	}
	
	private boolean checkInput(Toggle eventTypeToggle, String eventTitle, String eventDescription,
			LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		
		StringBuilder message = new StringBuilder();
		boolean isDurationEvent = isDurationEvent(eventTypeToggle);
		Timeline currentTimeline = container.getActiveTimeline();
		boolean inputOkay = true;
		
		if (eventTitle.isEmpty()) {
			inputOkay = false;
			message.append("- Title must not be empty.\n");
		} if (eventTypeToggle == null) {
			inputOkay = false;
			message.append("- An event type must be selected.\n");
		} else if (isDurationEvent && (startDate == null || startTime == null || endDate == null || endTime == null)) {	// valid duration dates and time?
			inputOkay = false;
			message.append("- A start and end date and time must be selected for duration events.\n");
		} else if (!isDurationEvent && (startDate == null || startTime == null)) {	// valid non-duration dates and time?
			inputOkay = false;
			message.append("- A start date and start time must be selected for non-duration events.\n");
		} else if (isDurationEvent && LocalDateTime.of(startDate, startTime).isAfter(LocalDateTime.of(endDate, endTime))) {
			inputOkay = false;
			message.append("- End Date can not be before Start Date.\n");
		} else if (!isDatesWithinTimeline(startDate, endDate, currentTimeline)) {
			inputOkay = false;
			message.append(String.format("- Event date(s) must be within range %s - %s.\n",
					currentTimeline.getStartDate(), currentTimeline.getEndDate()));
		}
		
		if (inputOkay) {
			return true;
		} else {
			message.insert(0, "The following input was invalid:\n");
			Alert alert = new Alert(AlertType.ERROR, message.toString(), ButtonType.OK);
			alert.showAndWait();
			return false;
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