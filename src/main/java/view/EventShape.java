package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * A class to create the shape of the event depinding on if it is duration or
 * non-duration events.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name EventShape.java
 */

public class EventShape {
	Shape eventShape;
	int width;
	int layoutX;
	int layoutY;
	boolean isDuration;

	public EventShape(boolean isDuration, int layoutX, int width) {
		
		this.layoutX = layoutX;
		this.width = width;
		this.isDuration = isDuration;
		
		if (isDuration) {

			Rectangle rect = new Rectangle(layoutX, 0, width, 27);
			rect.setFill(Color.TEAL);
			rect.setArcHeight(30);
			rect.setArcWidth(30);
			eventShape = rect;
		} else {
			this.width = 27;
			eventShape = new Circle(layoutX, 14, 14, Color.BLACK);
		}
	}
	
	public boolean isOverlapping(EventShape event){
		
		if(layoutX <= event.getLayoutX() && layoutX + width > event.getLayoutX() ||
				layoutX < event.getLayoutX() + event.getWidth() && layoutX < event.getLayoutX()){
			return true;
		} else{
			return false;
		}
	}

	public Shape getShape() {
		return eventShape;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}

	public void setShapeLayoutX(int X) {
		this.layoutX = X;
		eventShape.setLayoutX(X);
	}
	
	public int getLayoutX(){
		return layoutX;
	}

	public void setShapeLayoutY(int Y) {
		this.layoutX = Y;
	}
}
