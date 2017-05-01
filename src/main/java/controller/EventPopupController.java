package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import interfaces.EventPopupListener;

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

	@Override
	public void onOkayButtonClicked(String eventTitle, String eventDescription, LocalDate startDate,
		    						LocalTime startTime, LocalDate endDate, LocalTime endTime) {
		// TODO Auto-generated method stub
		
	}

}