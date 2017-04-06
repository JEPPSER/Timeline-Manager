package timeline;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class for drawing and handling the graphics for the TimelineManager.
 * 
 * @author Jesper Bergström
 * @version 0.00.00
 * @name UserInterface.java
 */
public class UserInterface extends StackPane {

	private TimelinePane tp;
	private ImageView deleteTimeline = new ImageView(new Image("file:src\\main\\resources\\DeleteTimeline.png"));
	private ImageView addEvent = new ImageView(new Image("file:src\\main\\resources\\AddEvent.png"));
	private ImageView addTimeline = new ImageView(new Image("file:src\\main\\resources\\TimelineButton.png"));
	private ImageView saveTimeline = new ImageView(new Image("file:src\\main\\resources\\SaveTimeline.png"));
	private ImageView openTimeline = new ImageView(new Image("file:src\\main\\resources\\OpenTimeline.png"));
	private ColorPicker color = new ColorPicker();

	/**
	 * Constructor that builds the graphical interface for the TimelineManager.
	 */
	public UserInterface() {

		SplitPane split = new SplitPane();

		tp = new TimelinePane();

		BorderPane menu = new BorderPane();

		StackPane stack = new StackPane();

		InnerShadow is = new InnerShadow();

		// Background for the menu.
		Rectangle bg = new Rectangle();
		bg.setFill(Color.GRAY);
		bg.setHeight(60);
		bg.setWidth(10);
		bg.setScaleX(200);
		bg.setEffect(is);

		HBox timelines = new HBox();

		// Empty filler spaces to fix alignment
		Pane filler1 = new Pane();
		filler1.setPrefSize(15, 10);
		Pane filler2 = new Pane();
		filler2.setPrefSize(30, 10);
		Pane filler3 = new Pane();
		filler3.setPrefWidth(15);

		// HBox for the color picker.
		HBox colorBox = new HBox();
		colorBox.getChildren().addAll(color, filler3);
		colorBox.setAlignment(Pos.CENTER);

		// MenuButton. From this, the user will be able to select which
		// timelines will be displayed.
		MenuButton mb = new MenuButton();
		mb.setPrefSize(100, 30);
		mb.setText("Timelines");

		// Temporary MenuItem.
		MenuItem m1 = new MenuItem();
		m1.setText("My Project");
		mb.getItems().add(m1);

		menu.setMaxHeight(60);
		menu.setMinHeight(60);
		timelines.setSpacing(5);
		timelines.getChildren().addAll(filler1, mb, addTimeline, saveTimeline, openTimeline, deleteTimeline);
		timelines.setAlignment(Pos.CENTER);
		menu.setLeft(timelines);
		menu.setRight(colorBox);

		stack.setMaxHeight(60);
		stack.setPrefWidth(800);

		stack.getChildren().addAll(bg, menu);

		// Have a SplitPane separate the menu and the TimelinePane.
		split.setOrientation(Orientation.VERTICAL);
		split.getItems().addAll(stack, tp);

		color.setOnAction(e -> {
			tp.setColor(color.getValue());
		});

		super.getChildren().addAll(split, addEvent);
		super.setAlignment(addEvent, Pos.BOTTOM_RIGHT);
	}
}
