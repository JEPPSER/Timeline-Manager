package interfaces;

import java.util.List;

/**
 * Interface used to listen for changes in the model class TimelineContainer.
 * 
 * MainController implements this interface so it can be notified whenever a timeline is added,
 * edited or removed from TimelineContainer.
 * 
 * In order to listen for changes to TimelineContainer, the object implementing this interface must 
 * first be registered as a listener using the registerListener method.
 * 
 * Implementing classes:
 * 				MainController
 * 
 * @author Mustafa Alsaid and Daniel Alm Grunström
 * @version 0.00.00
 * @name ModelChangedListener.java
 */
public interface ModelChangedListener {
	
	/**
	 * Called on the registered listener whenever a timeline is added, edited or deleted in TimelineContainer. 
	 * 
	 * @param timelines - The updated list of timelines
	 */
	//TODO:Add TimeLine Parameter.
	public void onModelChanged();

}
