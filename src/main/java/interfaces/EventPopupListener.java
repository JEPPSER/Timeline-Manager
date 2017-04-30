package interfaces;

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
	 * Method that handles when the user clicks the Okay button in the add/edit event popup.
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 */
	public void onOkayButtonClicked();
}