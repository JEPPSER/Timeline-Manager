package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TimeLine")
@XmlAccessorType(XmlAccessType.FIELD)


/**
 * TimeLine class currently contains the TimeLine Id, name and a list of its events with setters and getters.
 * Moreover it responsible for adding, updating and delete events. This class is a necessary for generating XML files.
 * 
 * @author Mustafa Alsaid
 * @version 0.00.00
 * @name TimeLine.java
 */

public class TimeLine {

	private static final AtomicInteger count = new AtomicInteger(0); //static field necessary to create auto generated id
	private int timeLineId;
	private String timeLineName;
	private Date startDate;
	private Date endDate;
	@XmlElement(name = "Event")
	private ArrayList<Event> eventList;
	
	/**
	 * Empty constructor.
	 */
	public TimeLine() {

	}
	/**
	 * Constructor takes timeLine Name as a parameter.
	 */
	public TimeLine(String name, Date startDate, Date endDate) {
		timeLineId = count.getAndIncrement(); //auto increment TimeLine Id//
		this.timeLineName = name;   
		this.startDate=startDate;
		this.endDate=endDate;
		eventList=new ArrayList<Event>();

	}

	/**
	 * Return string representation for a TimeLine Fields.
	 */
	@Override
	public String toString() {
		return "TimeLine [Id:" + timeLineId + " , Name:" + timeLineName + " , StartDate:" +startDate + " , EndDate:" + endDate+"]";
	}
	
	/**
	 * Return the Id of a TimeLine.
	 */
	public int getId() {
		return timeLineId;
	}
    
	/**
	 * Return the name of a TimeLine.
	 */
	public String getName() {
		return timeLineName;
	}
    
	/**
	 * Set the name of a TimeLine.
	 */
	public void setName(String name) {
		timeLineName = name;
	}

	
	/**
	 * Return the startDate of a TimeLine.
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Set the startDate of a TimeLine.
	 */
	public void setStartDate(Date startDate) {
		this.startDate=startDate;
	}
	
	/**
	 * Return the endDate of a TimeLine.
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Set the endDate of a TimeLine.
	 */
	public void setEndDate(Date endDate) {
		this.endDate=endDate;
	}
	
	/**
	 * Add an event of type Event.
	 */
	public void add(Event event) {
		eventList.add(event);
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

}