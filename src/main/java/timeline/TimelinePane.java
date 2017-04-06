package timeline;

import java.awt.Dimension;
import java.awt.Toolkit;

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

/**
 * Class drawing the graphics for how a timeline will be displayed. This class
 * can be added as a component in a user interface.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name TimelinePane.java
 */
public class TimelinePane extends ScrollPane {

	private Pane[] panes = new Pane[33];
	private StackPane stack = new StackPane();
	private HBox dates;

	/**
	 * Constructor that sets all the initial components in the TimelinePane.
	 */
	public TimelinePane() {

		super.setPrefSize(800, 400); // Default size

		Group root = new Group();

		VBox vbox = new VBox();
		vbox.setSpacing(3);

		dates = new HBox();

		// Get screen resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		dates.setPrefSize(screenSize.getWidth(), screenSize.getHeight());

		setColor(Color.WHITE);

		// Create the rows
		for (int i = 0; i < panes.length; i++) {

			panes[i] = new Pane();
			panes[i].setPrefHeight(30);
			panes[i].setPrefWidth(screenSize.getWidth());
		}

		vbox.getChildren().addAll(panes);

		stack.getChildren().addAll(dates, vbox);

		root.getChildren().add(stack);

		super.setContent(root);
	}

	/**
	 * Method that sets the color for and draws the columns in the TimelinePane.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {

		dates.getChildren().clear();

		BorderPane column;
		Text text;
		Rectangle rect;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Draw the columns
		for (int i = 0; i < 38; i++) {

			column = new BorderPane();
			column.setPrefWidth(50);

			rect = new Rectangle();
			rect.setWidth(50);
			rect.setHeight(screenSize.getHeight());
			rect.setStroke(Color.BLACK);
			rect.setOpacity(0.3);
			rect.setFill(color);

			text = new Text();
			text.setFont(Font.font("Arial", 18));
			text.setText(String.valueOf((i % 30) + 1));

			BorderPane txtContainer = new BorderPane();
			txtContainer.setCenter(text);

			column.setTop(txtContainer);
			column.setCenter(rect);
			dates.getChildren().add(column);
		}
	}
}
