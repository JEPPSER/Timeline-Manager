package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;

import interfaces.TimelineViewListener;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Timeline;

/**
 * Class drawing the graphics for how a timeline will be displayed. This class
 * can be added as a component in a user interface.
 * 
 * @author Jesper Bergström and Zacky Kharboutli
 * @version 0.00.00
 * @name TimelineView.java
 */
public class TimelineView extends ScrollPane {

	private final int ROWS = 30;
	private Pane[] panes = new Pane[ROWS];
	private StackPane stack = new StackPane();
	private HBox dates;
	private TimelineViewListener listener;
	private Timeline currentTimeline;

	/**
	 * Constructor that sets all the initial components in the TimelineView.
	 */
	public TimelineView() {

		super.setPrefSize(800, 400); // Default size

		Group root = new Group();

		VBox vbox = new VBox();
		vbox.setSpacing(3);

		dates = new HBox();

		// Create the rows
		for (int i = 0; i < panes.length; i++) {

			panes[i] = new Pane();
			panes[i].setPrefHeight(30);
		}

		vbox.getChildren().addAll(panes);

		stack.getChildren().addAll(dates, vbox);

		root.getChildren().add(stack);

		super.setContent(root);
	}
	
	/**
	 * Method that sets a timeline to be displayed in the timeline view.
	 * @param Timeline
	 */
	public void setTimeline(Timeline timeline){
		
		currentTimeline = timeline;
		drawColumns();
		// 1. Convert list of events in timeline to a list of EventShapes.
		// 2. Use algorithm to place the EventShapes in the timeline view.
	}
	
	/**
	 * Method that registers the listeners for the timeline view.
	 * @param listener
	 */
	public void registerListener(TimelineViewListener listener){
		this.listener = listener;
	}

	/**
	 * Method that draws the columns in the TimelinePane.
	 */
	private void drawColumns() {

		dates.getChildren().clear();

		BorderPane column;
		Text text;
		Rectangle rect;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int days = 0;

		// Get the duration of the timeline.
		if(currentTimeline != null){
			LocalDate temp = currentTimeline.getStartDate();
			for(int i=0; !temp.equals(currentTimeline.getEndDate()); i++){
				temp = temp.plusDays(1);
				days = i;
			}
			days++;
		}	
		
		// Draw the columns
		for (int i = 0; i <= days; i++) {

			column = new BorderPane();
			column.setPrefWidth(50);

			rect = new Rectangle();
			rect.setWidth(50);
			rect.setHeight(screenSize.getHeight());
			rect.setStroke(Color.BLACK);
			rect.setOpacity(0.3);
			rect.setFill(Color.WHITE);

			String day = String.valueOf(currentTimeline.getStartDate().plusDays(i).getDayOfMonth());
			text = new Text();
			text.setFont(Font.font("Arial", 18));
			text.setText(day);

			BorderPane txtContainer = new BorderPane();
			txtContainer.setCenter(text);
			txtContainer.setPrefHeight(20);

			column.setTop(txtContainer);
			column.setBottom(rect);
			dates.getChildren().add(column);
		}
	}
}
