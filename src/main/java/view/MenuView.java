package view;

import interfaces.MenuListener;
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
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name MenuView.java
 */
public class MenuView extends StackPane {

	private Button deleteTimeline;
	private Button addTimeline;
	private Button saveTimeline;
	private Button openTimeline;

	public MenuView() {
		
		deleteTimeline = new Button();
		addTimeline = new Button();
		saveTimeline = new Button();
		openTimeline = new Button();

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
		saveTimeline.setPrefSize(30, 30);
		saveTimeline.setText("S");
		openTimeline.setPrefSize(30, 30);
		openTimeline.setText("L");

		menu.setMaxHeight(60);
		menu.setMinHeight(60);
		timelineButtons.setSpacing(5);
		timelineButtons.getChildren().addAll(filler1, mb, addTimeline, deleteTimeline, saveTimeline, openTimeline);
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
	
	public void registerListener(MenuListener listener){
		addTimeline.setOnAction(e -> {
			listener.onAddButtonClicked();
		});
		
		deleteTimeline.setOnAction(e -> {
			listener.onDeleteButtonClicked();
		});
		
		saveTimeline.setOnAction(e -> {
			//listener.onSaveButtonClicked(file);
		});
		
		openTimeline.setOnAction(e -> {
			//listener.onOpenButtonClicked(file);
		});
	}
}
