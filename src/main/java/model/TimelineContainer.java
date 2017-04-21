package model;

import interfaces.ModelChangedListener;

/**
 * Main model class of the application. Holds all the timelines that are currently loaded into
 * the application. Has methods for managing the list of timelines, for example adding a timeline.
 * 
 * Notifies MainController via the ModelChangedListener set in registerListener whenever a 
 * change occurs to the list of timelines.
 * 
 * @author Daniel Alm Grundstrom
 * @version 0.00.00
 * @name MainController.java
 */
public class TimelineContainer {

	private ModelChangedListener listener;
	
	/**
	 * Adds a timeline to this TimelineContainer.
	 */
	public void addTimeline() {
		// Logic for adding timeline
		System.out.println("TimelineContainer: Request to add timeline received");
		
		listener.onModelChanged(); // tell listener that a timeline has been added
	}
	
	/**
	 * Registers an object as a listener to this TimelineContainer. It will then be notified
	 * whenever a change occurs to the timelines.
	 * 
	 * @param listener - the listener to register, must implement the interface ModelChangedListener
	 */
	public void registerListener(ModelChangedListener listener) {
		this.listener = listener;
	}
}
