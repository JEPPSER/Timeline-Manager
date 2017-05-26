package interfaces;

import java.time.LocalDateTime;

import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;

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
	 * @param eventTypeToggle - The selected toggle the user uses to select if the event should be duration event or a non-duration event.
	 * @param eventTitle - The event title that the user entered
	 * @param eventDescription - The event description that the user entered
	 * @param startDate - The start date and time the user selected. If the event is non-duration, this is the only date used.
	 * @param endDate - The end date and time the user selected. Is null if the event is non-duration.
	 */
	public void onAddButtonClicked(Toggle eventTypeToggle, String eventTitle, String eventDescription,
								   LocalDateTime startDate, LocalDateTime endDate, Color color);
	
	/**
	 * Method that handles when the user clicks the Edit button in the edit event popup.
	 * 
	 * @param eventId - The ID of the event to edit
	 * @param eventTypeToggle - The selected toggle the user uses to select if the event should be duration event or a non-duration event.
	 * @param eventTitle - The event title that the user entered
	 * @param eventDescription - The event description that the user entered
	 * @param startDate - The start date and time the user selected. If the event is non-duration, this is the only date used.
	 * @param endDate - The end date and time the user selected. Is null if the event is non-duration.
	 */
	public void onEditButtonClicked(int eventId, Toggle eventTypeToggle, String eventTitle, String eventDescription,
								    LocalDateTime startDate, LocalDateTime endDate, Color color);
}