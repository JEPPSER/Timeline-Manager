package view;

import java.util.List;

import de.jensd.fx.fontawesome.*;
import interfaces.MenuListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Timeline;

/**
 * Class for drawing the graphics for the menu.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name MenuView.java
 */
public class MenuView extends StackPane {
	private Button themes;
	private Button deleteTimeline;
	private Button addTimeline;
	private Button saveTimeline;
	private Button openTimeline;
	private MenuButton loadedTimelines;
	private MenuListener listener;
	private final ToggleGroup group = new ToggleGroup();

	/**
	 * Constructor setting all the components.
	 */
	public MenuView() {

		deleteTimeline = new Button();
		addTimeline = new Button();
		saveTimeline = new Button();
		openTimeline = new Button();
		
		themes = new Button("Themes");
		themes.setPrefSize(100, 30);


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
		loadedTimelines.setPrefSize(100, 30);
		loadedTimelines.setStyle(AwesomeStyle.RED.getStylePath());
		addTimeline = AwesomeDude.createIconButton(AwesomeIcon.PLUS_SIGN_ALT, "", "20", "15", ContentDisplay.CENTER);
		deleteTimeline = AwesomeDude.createIconButton(AwesomeIcon.TRASH, "", "20", "15", ContentDisplay.CENTER);
		saveTimeline = AwesomeDude.createIconButton(AwesomeIcon.SAVE, "", "20", "15", ContentDisplay.CENTER);
		openTimeline = AwesomeDude.createIconButton(AwesomeIcon.FOLDER_OPEN_ALT, "", "20", "15", ContentDisplay.CENTER);

		menu.setMaxHeight(60);
		menu.setMinHeight(60);
		timelineButtons.setSpacing(5);
		timelineButtons.getChildren().addAll(filler1, loadedTimelines, addTimeline, deleteTimeline, saveTimeline,
				openTimeline,themes);
		timelineButtons.setAlignment(Pos.CENTER);
		menu.setLeft(timelineButtons);

		ToggleButton tb1 = new ToggleButton("Week");
		tb1.setToggleGroup(group);
		tb1.setPrefWidth(70);
		ToggleButton tb2 = new ToggleButton("Month");
		tb2.setToggleGroup(group);
		tb2.setPrefWidth(70);
		tb2.setSelected(true);
		tb2.setDisable(true);
		ToggleButton tb3 = new ToggleButton("Year");
		tb3.setToggleGroup(group);
		tb3.setPrefWidth(70);
		HBox toggleButtons = new HBox();
		toggleButtons.getChildren().addAll(tb1, tb2, tb3, filler2);
		toggleButtons.setAlignment(Pos.CENTER);
		menu.setRight(toggleButtons);
		
		super.setMaxHeight(60);
		super.getChildren().addAll(bg, menu);
	}
	
	/**
	 * Method for getting the ToggleGroup with all ToggleButtons.
	 * 
	 * @return ToggleGroup
	 */
	public ToggleGroup getToggleGroup(){
		return group;
	}

	/**
	 * Method for getting the addTimeline Button
	 * 
	 * @return addTimeline Button
	 */
	public Button getAddTimelineButton() {
		return addTimeline;
	}

	/**
	 * Method for getting the openTimeline Button
	 * 
	 * @return openTimeline Button
	 */
	public Button getOpenTimelineButton() {
		return openTimeline;
	}

	/**
	 * Method for getting the saveTimeline Button
	 * 
	 * @return saveTimeline Button
	 */
	public Button getSaveTimelineButton() {
		return saveTimeline;
	}

	/**
	 * Method for getting the deleteTimeline Button
	 * 
	 * @return deleteTimeline Button
	 */
	public Button getDeleteTimelineButton() {
		return deleteTimeline;
	}

	/**
	 * Method for updating the dropdown menu of loaded timelines
	 * 
	 * @param timelines - the list of currently loaded timelines
	 * @param active - the currently active timeline
	 */
	public void updateTimelineDropdown(List<Timeline> timelines, Timeline active) {
		loadedTimelines.getItems().clear();
		
		for (Timeline t : timelines) {
			MenuItem item = new MenuItem();
			item.setUserData(t);
			
			if (t.getHasUnsavedChanges()) {
				item.setText("* " + t.getName());
			} else {
				item.setText(t.getName());
			}
			
			item.setOnAction(e -> listener.onNewTimelineSelected(t));
			loadedTimelines.getItems().add(item);
			
			if (t == active) {
				loadedTimelines.setText(item.getText());
			}
		}
		
		if (active == null) {
			loadedTimelines.setText("Timelines");
		}
		
	}
	
	public Button getThemesButton(){
		return themes;
	}

	/**
	 * Method for registering the listener for the MenuView
	 * 
	 * @param MenuListener
	 */
	public void registerListener(MenuListener listener) {

		this.listener = listener;
		
		Stage stage = (Stage) getScene().getWindow();

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
