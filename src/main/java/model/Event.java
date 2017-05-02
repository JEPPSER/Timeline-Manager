package model;

import java.time.LocalDate;

/**
 * Event class contains the eventId, eventName, eventDescription, eventStartDate, eventEndDate for
 * DURATION event and eventType with setters and getters.
 *  
 *  
 *  
 * @author Mustafa Alsaid
 * @version 0.00.00
 * @name Event.java
 */
public class Event {
	
	private int eventId;
	private String eventName;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;

	public enum EventType {
		DURATION, NON_DURATION
	}

	private EventType type;

	public Event(int eventId, String eventName, String description, LocalDate startDate, LocalDate endDate,
			EventType type) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.startDate = startDate;
		this.type = type;
		if (type == EventType.DURATION) {
			this.endDate = endDate;
		}
	}
    
	/**
	 * Empty constructor.
	 */
	public Event() {

	}
    
	/**
	 * Returns string representation for a Event Fields.
	 */
	@Override
	public String toString() {
		if (type == EventType.DURATION) {

			return "Event [ ID:" + eventId + " , Name:" + eventName + " , StartDate:" + startDate + " , EndDate:"
					+ endDate + " , EventType" + EventType.DURATION + "]";
		} else {
			return "Event [ ID:" + eventId + " , Name:" + eventName + " , StartDate:" + startDate + " , EventType"
					+ EventType.NON_DURATION + "]";
		}
	}
	
	
	/**
	 * Returns the id of an Event.
	 */
	public int getId() {
		return eventId;
	}
    
	/**
	 * Set the id of an Event .
	 */
	public void setId(int id) {
		eventId = id;
	}
	
	/**
	 * Set the name of a TimeLine.
	 */
	public String getEventName() {
		return eventName;
	}
	
	/**
	 * Set the name of an Event .
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
    
	/**
	 * Returns the description of an Event.
	 */
	public String getDescription() {
		return description;
	}
    
	/**
	 * Set the description of an Event .
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Returns the start date of an Event.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the start date of an Event .
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the end date of an Event.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the end date of an Event .
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
    
	/**
	 * Returns the type of an Event.
	 */
	public EventType getType() {
		return type;
	}
	/**
	 * Set the type of an Event .
	 */
	public void setType(EventType type) {
		this.type = type;
	}
}