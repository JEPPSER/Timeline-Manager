package interfaces;

import javafx.stage.Stage;

/**
 * Interface used to listen for user interaction events (button clicks, etc.) on the timeline view.
 * 
 * TimelineViewController implements this interface so that TimelineView can notify the controller
 * whenever a user clicks on the add event button, hovers the mouse over an event, clicks on the 
 * edit event button or clicks on the delete event button.
 * 
 * For the implementing class to be notified, it must first be registered as a listener to TimelineView
 * by calling the method registerListener.
 * 
 * Implementing classes:
 * 				TimelineViewController
 * 
 * @author Mustafa Alsaid and Daniel Alm Grunstrom
 * @version 0.00.00
 * @name ModelChangedListener.java
 */
public interface TimelineViewListener {

	/**
	 * Called whenever the user clicks on the add event button.
	 */
	public void onAddEventClicked(Stage stage);
	
	/**
	 * Called whenever the user hovers the mouse over an event. 
	 * 
	 * @param name - name of the event which the user hovered over
	 */
	public void onMouseOverEvent(String name);
	
	/**
	 * Called whenever the user clicks on the delete event button located
	 * on the popup displayed when the user hovers over an event.
	 * 
	 * @param name - name of the event which the user wants to delete
	 */
	public void onDeleteEventClicked(String name);
	
	/**
	 * Called whenever the user clicks on the edit event button located
	 * on the popup displayed when the user hovers over an event.
	 * 
	 * @param name - name of the event which the user wants to edit
	 */
	public void onEditEventClicked(String name);
}
