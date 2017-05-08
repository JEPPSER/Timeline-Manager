package view;

import de.jensd.fx.fontawesome.*;
import interfaces.MenuListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
	private MenuButton loadedTimelines;

	/**
	 * Constructor setting all the components.
	 */
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
		loadedTimelines = new MenuButton();
		loadedTimelines.setText("Timelines");
		loadedTimelines.setPrefSize(100, 35);
		loadedTimelines.setStyle(AwesomeStyle.RED.getStylePath());
		addTimeline = AwesomeDude.createIconButton(AwesomeIcon.PLUS_SIGN_ALT, "","20", "15", ContentDisplay.CENTER);
		//addTimeline.setPrefSize(30, 30);
		//addTimeline.setText("+");
		deleteTimeline=AwesomeDude.createIconButton(AwesomeIcon.TRASH, "","20", "15", ContentDisplay.CENTER);
		//deleteTimeline.setText("-");
		saveTimeline=AwesomeDude.createIconButton(AwesomeIcon.SAVE, "","20", "15", ContentDisplay.CENTER);
		//saveTimeline.setText("S");
		openTimeline=AwesomeDude.createIconButton(AwesomeIcon.FOLDER_OPEN_ALT, "","20", "15", ContentDisplay.CENTER);
		//openTimeline.setText("L");

		menu.setMaxHeight(60);
		menu.setMinHeight(60);
		timelineButtons.setSpacing(5);
		timelineButtons.getChildren().addAll(filler1, loadedTimelines, addTimeline, deleteTimeline, saveTimeline, openTimeline);
		timelineButtons.setAlignment(Pos.CENTER);
		menu.setLeft(timelineButtons);
		super.setMaxHeight(60);
		super.getChildren().addAll(bg, menu);
	}

	/**
	 * Method for getting the addTimeline Button
	 * @return addTimeline Button
	 */
	public Button getAddTimelineButton() {
		return addTimeline;
	}
	
	/**
	 * Method for getting the openTimeline Button
	 * @return openTimeline Button
	 */
	public Button getOpenTimelineButton() {
		return openTimeline;
	}
	
	/**
	 * Method for getting the saveTimeline Button
	 * @return saveTimeline Button
	 */
	public Button getSaveTimelineButton() {
		return saveTimeline;
	}
	
	/**
	 * Method for getting the deleteTimeline Button
	 * @return deleteTimeline Button
	 */
	public Button getDeleteTimelineButton() {
		return deleteTimeline;
	}
	
	/**
	 * Method for getting the MenuButton containing
	 * the loaded timelines.
	 * @return Loaded timelines MenuButton
	 */
	public MenuButton getLoadedTimelines(){
		return loadedTimelines;
	}
	
	/**
	 * Method for registering the listener for the MenuView 
	 * @param MenuListener
	 */
	public void registerListener(MenuListener listener){
		
		Stage stage = (Stage)getScene().getWindow(); 
		
		addTimeline.setOnAction(e -> {
			listener.onAddButtonClicked(this);
		});
		
		deleteTimeline.setOnAction(e -> {
			listener.onDeleteButtonClicked();
		});
		
		saveTimeline.setOnAction(e -> {
			listener.onSaveButtonClicked(stage);
		});
		
		openTimeline.setOnAction(e -> {
			listener.onOpenButtonClicked(stage);
		});
	}
}
