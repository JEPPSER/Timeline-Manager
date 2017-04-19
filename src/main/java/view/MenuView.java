package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class for drawing the graphics for the menu.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name MenuView.java
 */
public class MenuView extends StackPane {

	private Button deleteTimeline = new Button();
	private Button addTimeline = new Button();

	public MenuView() {

		InnerShadow is = new InnerShadow();

		BorderPane menu = new BorderPane();

		// Background for the menu.
		Rectangle bg = new Rectangle();
		bg.setFill(Color.GRAY);
		bg.setHeight(60);
		bg.setWidth(10);
		bg.setScaleX(200);
		bg.setEffect(is);

		HBox timelineButtons = new HBox();

		// Empty filler spaces to fix alignment
		Pane filler1 = new Pane();
		filler1.setPrefSize(15, 10);
		Pane filler2 = new Pane();
		filler2.setPrefSize(30, 10);
		Pane filler3 = new Pane();
		filler3.setPrefWidth(15);

		// MenuButton. From this, the user will be able to select which
		// timelines will be displayed.
		MenuButton mb = new MenuButton();
		mb.setPrefSize(100, 30);
		mb.setText("Timelines");

		// Temporary MenuItem.
		MenuItem m1 = new MenuItem();
		m1.setText("My Project");
		mb.getItems().add(m1);

		addTimeline.setPrefSize(30, 30);
		addTimeline.setText("+");
		deleteTimeline.setPrefSize(30, 30);
		deleteTimeline.setText("-");

		menu.setMaxHeight(60);
		menu.setMinHeight(60);
		timelineButtons.setSpacing(5);
		timelineButtons.getChildren().addAll(filler1, mb, addTimeline, deleteTimeline);
		timelineButtons.setAlignment(Pos.CENTER);
		menu.setLeft(timelineButtons);

		super.setMaxHeight(60);
		super.getChildren().addAll(bg, menu);
	}

	public Button getAddTimelineButton() {
		return addTimeline;
	}

	public Button getDeleteTimelineButton() {
		return deleteTimeline;
	}

}
