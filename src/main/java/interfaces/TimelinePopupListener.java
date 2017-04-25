package interfaces;

import java.time.LocalDate;

/**
 * Listener for the TimelinePopup. This listener will handle all actions
 * performed by the user in the TimelinePopup.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupListener.java
 */
public interface TimelinePopupListener {

	/**
	 * Method that handles when the user clicks the Save button.
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 */
	public void onSaveButtonClicked(String title, LocalDate startDate, LocalDate endDate);
}
