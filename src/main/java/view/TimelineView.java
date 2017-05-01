package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

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
import model.Event;
import model.Timeline;
import model.Event.EventType;

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
	private int width = 50; // For time perspective
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
	 * 
	 * @param Timeline
	 */
	public void setTimeline(Timeline timeline) {

		currentTimeline = timeline;
		drawColumns();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		formatter = formatter.withLocale(Locale.ENGLISH);

		// Temporary list of events.
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(new Event(1, "one", "desc", LocalDate.parse("2017-May-04", formatter),
				LocalDate.parse("2017-May-07", formatter), EventType.DURATION));
		events.add(new Event(2, "two", "desc", LocalDate.parse("2017-May-05", formatter),
				LocalDate.parse("2017-May-07", formatter), EventType.DURATION));
		events.add(new Event(3, "three", "desc", LocalDate.parse("2017-May-06", formatter),
				LocalDate.parse("2017-May-10", formatter), EventType.DURATION));
		events.add(new Event(4, "four", "desc", LocalDate.parse("2017-May-08", formatter),
				LocalDate.parse("2017-May-12", formatter), EventType.DURATION));
		events.add(new Event(5, "five", "desc", LocalDate.parse("2017-May-07", formatter),
				LocalDate.parse("2017-May-12", formatter), EventType.NON_DURATION));

		ArrayList<EventShape> shapeList = new ArrayList<EventShape>();

		int trueWidth = width + 1;

		// Convert list of events in timeline to a list of EventShapes.
		for (int i = 0; i < events.size(); i++) {
			EventType type = events.get(i).getType();
			int length;
			int start = (int) (ChronoUnit.DAYS.between(timeline.getStartDate(), events.get(i).getStartDate()));
			if (events.get(i).getType() == EventType.DURATION) {
				length = (int) (ChronoUnit.DAYS.between(events.get(i).getStartDate(), events.get(i).getEndDate()));
			} else {
				length = 1;
			}
			EventShape shape = new EventShape(type, start * trueWidth, length * trueWidth);
			shapeList.add(shape);
		}

		// List that holds all added events.
		ArrayList<EventShape> added = new ArrayList<EventShape>();

		// Algorithm for placing the EventShapes in the timeline view.
		for (int i = 1; i < panes.length; i++) { // Clear the timeline view.
			panes[i].getChildren().clear();
		}

		for (int i = 0; i < shapeList.size(); i++) {

			if (i == 0) { // First event.
				panes[1].getChildren().add(shapeList.get(i).getShape());
				added.add(shapeList.get(i));
			} else {
				boolean found = false;
				added.add(shapeList.get(i));

				for (int j = 1; !found; j++) {
					panes[j].getChildren().add(shapeList.get(i).getShape());

					for (int k = 0; k < added.size() - 1; k++) {
						if (shapeList.get(i).isOverlapping(added.get(k))
								&& panes[j].getChildren().contains(added.get(k).getShape())) {
							panes[j].getChildren().remove(shapeList.get(i).getShape());
							found = false;
							break;
						}
						found = true;
					}
				}
			}
		}
	}

	/**
	 * Method that registers the listeners for the timeline view.
	 * 
	 * @param listener
	 */
	public void registerListener(TimelineViewListener listener) {
		this.listener = listener;
	}

	/**
	 * Private method that draws the columns in the TimelinePane.
	 */
	private void drawColumns() {

		dates.getChildren().clear();

		BorderPane column;
		Text text;
		Rectangle rect;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int days = 0;

		// Get the duration of the timeline.
		if (currentTimeline != null) {
			LocalDate temp = currentTimeline.getStartDate();
			for (int i = 0; !temp.equals(currentTimeline.getEndDate()); i++) {
				temp = temp.plusDays(1);
				days = i;
			}
			days++;
		}

		// Draw the columns
		for (int i = 0; i <= days; i++) {

			column = new BorderPane();
			column.setPrefWidth(width);

			rect = new Rectangle();
			rect.setWidth(width);
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
