package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import model.Event.EventType;

@XmlRootElement(name = "Timeline")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * TimeLine class currently contains the TimeLine Id, name and a list of its
 * events with setters and getters. Moreover it responsible for adding, updating
 * and delete events. This class is a necessary for generating XML files.
 * 
 * @author Mustafa Alsaid
 * @version 0.00.00
 * @name Timeline.java
 */

public class Timeline {

	@XmlElement(name="name")
	private String timelineName;
	@XmlJavaTypeAdapter(value = io.LocalDateAdapter.class)
	private LocalDate startDate;
	@XmlJavaTypeAdapter(value = io.LocalDateAdapter.class)
	private LocalDate endDate;
	@XmlElement(name = "Event")
	private ArrayList<Event> eventList;
	private String path = "";

	/**
	 * Empty constructor.
	 */
	public Timeline() {

	}

	/**
	 * Constructor takes timeLine Name as a parameter.
	 */
	public Timeline(String name) {
		this.timelineName = name;
		eventList = new ArrayList<Event>();

	}

	/**
	 * Return string representation for a TimeLine Fields.
	 */
	@Override
	public String toString() {
		return "TimeLine [ Name:" + timelineName + " , StartDate:" + startDate + " , EndDate:"
				+ endDate + "]";
	}

	/**
	 * Return the name of a TimeLine.
	 */
	public String getName() {
		return timelineName;
	}

	/**
	 * Set the name of a TimeLine.
	 */
	public void setName(String name) {
		timelineName = name;
	}

	/**
	 * Return the startDate of a TimeLine.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Set the startDate of a TimeLine.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Return the endDate of a TimeLine.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Set the endDate of a TimeLine.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Add an event of type Event.
	 */
	public void add(String name, String desc, LocalDateTime start, LocalDateTime end, EventType type) {
		eventList.add(new Event(getMaxId(), name, desc, start, end, type));
	}
	
	/**
	 * Update an event of type Event.
	 */
	public void update(Event event, String name, String desc, LocalDateTime start, LocalDateTime end){
		eventList.get(eventList.indexOf(event)).setEventName(name);
		eventList.get(eventList.indexOf(event)).setDescription(desc);
		eventList.get(eventList.indexOf(event)).setStartDate(start);
		if (event.getType() == EventType.DURATION) {
			eventList.get(eventList.indexOf(event)).setEndDate(end);
		}
	}
	
	/**
	 * Delete an event of type Event.
	 */
	public void delete(Event event){
		eventList.remove(event);
	}

	/**
	 * Return a list of type Event.
	 */
	public ArrayList<Event> getList() {
		return eventList;
	}

	/**
	 * Set eventList.
	 */
	public void setList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}
	
	/**
	 * Get path of the timeline file. The timeline will only have a path when
	 * it has been saved.
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Set the path of the file to this timeline.
	 * @param path
	 */
	public void setPath(String path){
		this.path = path;
	}
	
	/**
	 * Return auto increment id for an event.
	 */
	public int getMaxId() {
		Collections.sort(this.eventList, new Comparator<Event>() {
			@Override
			public int compare(Event first, Event second) {
				return first.getId() - second.getId();
			}
		});
		return this.eventList.isEmpty() ? 1
				: this.eventList.get(this.eventList.size() - 1).getId() + 1;
	}
}