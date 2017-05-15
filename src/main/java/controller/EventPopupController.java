package controller;

import java.time.LocalDateTime;

import interfaces.EventPopupListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;
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
	public void onAddButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription,
			LocalDateTime startDate, LocalDateTime endDate, Color color) {
		
		if (checkInput(eventTypeToggle, eventTitle, eventDescription, startDate, endDate)) {
			EventType type = isDurationEvent(eventTypeToggle) ? EventType.DURATION : EventType.NON_DURATION;
			container.addEvent(eventTitle, eventDescription, startDate, endDate, type, color);
			eventPopup.close();
		}
	}
	
	@Override
	public void onEditButtonClicked(int eventId, Toggle eventTypeToggle, String eventTitle, String eventDescription,
			LocalDateTime startDate, LocalDateTime endDate, Color color) {
		
		if (checkInput(eventTypeToggle, eventTitle, eventDescription, startDate, endDate)) {
			EventType newType = isDurationEvent(eventTypeToggle) ? EventType.DURATION : EventType.NON_DURATION;
			container.editEvent(eventId, eventTitle, eventDescription, startDate, endDate, newType, color);
			eventPopup.close();
		}
		
	}
	
	private boolean checkInput(Toggle eventTypeToggle, String eventTitle, String eventDescription,
			LocalDateTime startDate, LocalDateTime endDate) {
		
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
		} else if (isDurationEvent && (startDate == null || endDate == null)) {	// valid duration dates and time?
			inputOkay = false;
			message.append("- A start and end date and time must be selected for duration events.\n");
		} else if (!isDurationEvent && (startDate == null)) {	// valid non-duration dates and time?
			inputOkay = false;
			message.append("- A start date and start time must be selected for non-duration events.\n");
		} else if (isDurationEvent && startDate.isAfter(endDate)) {
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
			
			// remove last instance of '\n' in the stringbuilder
			int index = message.lastIndexOf("\n");
			if (index >= 0) {
				message = message.replace(index, index + 1, "");
			}
			
			Alert alert = new Alert(AlertType.ERROR, message.toString(), ButtonType.OK);
			alert.showAndWait();
			return false;
		}
	}
	
	private boolean isDurationEvent(Toggle toggle) {
		return toggle != null && toggle.getUserData().toString().equals("duration");
	}
	
	private boolean isDatesWithinTimeline(LocalDateTime eventStart, LocalDateTime eventEnd, Timeline timeline) {
		if (eventEnd == null) { // non-duration event?
			return eventStart.toLocalDate().minusDays(1).isBefore(timeline.getEndDate()) 
					&& eventStart.toLocalDate().plusDays(1).isAfter(timeline.getStartDate());
		} else {
			return eventStart.toLocalDate().minusDays(1).isBefore(timeline.getEndDate()) 
					&& eventStart.toLocalDate().plusDays(1).isAfter(timeline.getStartDate())
					&& eventEnd.toLocalDate().plusDays(1).isAfter(timeline.getStartDate()) 
					&& eventEnd.toLocalDate().minusDays(1).isBefore(timeline.getEndDate());
		}
	}

}