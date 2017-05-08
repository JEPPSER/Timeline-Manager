package model;

import java.time.LocalDate;
import java.util.ArrayList;

import interfaces.ModelChangedListener;
import model.Event.EventType;

/**
 * Main model class of the application. Holds all the timelines that are currently loaded into
 * the application and a reference to the currently active timeline, i.e. the one the user has opened and is working on.
 * 
 * Has methods for managing the list of timelines, for example adding a timeline.
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
	private ArrayList<Timeline> timelines;
	private Timeline activeTimeline;
	
	public TimelineContainer() {
		timelines = new ArrayList<Timeline>();
	}
	
	/**
	 * Adds a timeline to this TimelineContainer.
	 */
	public void addTimeline(Timeline timeline) {
		System.out.println("TimelineContainer: Request to add timeline received");
		
		timelines.add(timeline);
		activeTimeline = timeline; // Set the newly added timeline to be the active one
		
		listener.onModelChanged(timelines, activeTimeline); // tell listener that a timeline has been added
	}
	
	/**
	 * Adds an event to the currently active timeline.
	 */
	public void addEvent(String title, String description, LocalDate start, LocalDate end, EventType type) {
		if (activeTimeline != null) {
			activeTimeline.add(title, description, start, end, type);
			listener.onModelChanged(timelines, activeTimeline);
		}
	}
	
	/**
	 * Edits an existing event in the currently active timeline
	 */
	public void editEvent(int id, String title, String description, LocalDate start, LocalDate end, EventType type) {
		Event eventToEdit = getEventById(id);
		
		if (eventToEdit != null) {
			activeTimeline.update(eventToEdit, title, description, start, end, type);
			listener.onModelChanged(timelines, activeTimeline);
		}
	}
	
	/**
	 * Fetches the currently active timeline. The active timeline is the timeline the user has selected in the 
	 * list of timelines and the one that is currently displayed in the view.
	 *  
	 * @return the active timeline
	 */
	public Timeline getActiveTimeline() {
		return activeTimeline;
	}
	
	/**
	 * Sets the active timeline.
	 */
	public void setActiveTimeline(Timeline activeTimeline) {
		this.activeTimeline = activeTimeline;
		listener.onModelChanged(timelines, activeTimeline);
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
	
	/**
	 * Method for getting a list of the timelines.
	 */
	public ArrayList<Timeline> getTimelines(){
		return timelines;
	}
	
	private Event getEventById(int id) {
		for (Event e : activeTimeline.getList()) {
			if (e.getId() == id) {
				return e;
			}
		}
		
		return null;
	}
}
