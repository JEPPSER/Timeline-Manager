package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Event;
import model.Event.EventType;

/**
 * A class to create the shape of the event depending on if it is duration or
 * non-duration events.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name EventShape.java
 */

public class EventShape {
	private Shape eventShape;
	private Event event;
	private int width;
	private int layoutX;
	private Color color;

	/**
	 * Constructor that sets the properties and depending on the EventType,
	 * creates either a circle or a rectangle.
	 * 
	 * @param isDuration
	 * @param layoutX
	 * @param width
	 */
	public EventShape(Event event, EventType isDuration, int layoutX, int width, Color color) {

		this.layoutX = layoutX;
		this.width = width;
		this.event = event;

		if (isDuration == EventType.DURATION) {
			Rectangle rect = new Rectangle(layoutX, 0, width, 27);
			rect.setFill(color);
			rect.setArcHeight(30);
			rect.setArcWidth(30);
			eventShape = rect;
		} else {
			this.width = 27;
			eventShape = new Circle(layoutX, 14, 14, Color.BLACK);
		}
	}

	/**
	 * Method that checks if two events overlap each other.
	 * 
	 * @param event
	 * @return boolean
	 */
	public boolean isOverlapping(EventShape event) {

		if (layoutX <= event.getLayoutX() && layoutX + width > event.getLayoutX()
				|| layoutX < event.getLayoutX() + event.getWidth() && layoutX >= event.getLayoutX()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that returns the Event that the EventShape object is representing.
	 * 
	 * @return event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Method that sets what event the EventShape object should represent.
	 * 
	 * @param event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Method that returns the shape of the event.
	 * 
	 * @return Event shape
	 */
	public Shape getShape() {
		return eventShape;
	}

	/**
	 * Method that sets the width of the event shape (duration).
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Method for getting the width of the event shape.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method that sets the x layout of the event shape.
	 * 
	 * @param X
	 */
	public void setLayoutX(int X) {
		this.layoutX = X;
		eventShape.setLayoutX(X);
	}

	/**
	 * Method for getting the x layout of the event shape.
	 * 
	 * @return X
	 */
	public int getLayoutX() {
		return layoutX;
	}
}
