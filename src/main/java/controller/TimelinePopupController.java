package controller;

import java.time.LocalDate;

import interfaces.TimelinePopupListener;

/**
 * Controller that handles all actions made by the user
 * in the TimelinePopup. The purpose of the TimelinePopup
 * is to create a timeline.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class TimelinePopupController implements TimelinePopupListener{

	@Override
	public void onSaveButtonClicked(String title, LocalDate startDate, LocalDate endDate) {
		System.out.println("Title: " + title);
		System.out.println("Start Date: " + startDate);
		System.out.println("End Date: " + endDate);
	}
}
