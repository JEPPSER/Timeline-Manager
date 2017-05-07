package interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.scene.control.Toggle;

/**
 * Listener for the EventPopup. This listener will handle all actions
 * performed by the user in the EventPopup.
 * 
 * @author Daniel Alm Grundström
 * @version 0.00.00
 * @name EventPopupListener.java
 */
public interface EventPopupListener {
	
	/**
	 * Method that handles when the user clicks the Add button in the add event popup.
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 */
	public void onAddButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
								    LocalTime startTime, LocalDate endDate, LocalTime endTime);
	
	/**
	 * Method that handles when the user clicks the Edit button in the edit event popup.
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 */
	public void onEditButtonClicked(int eventID, Toggle eventTypeToggle, String eventTitle, String eventDescription, LocalDate startDate,
								    LocalTime startTime, LocalDate endDate, LocalTime endTime);
}