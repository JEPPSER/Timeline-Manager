package view;

import interfaces.EventPopupListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A popup window that pops up when the add event button is pressed and when an event is edited.
 * Used by user to enter the title, description, event type (Duration or Non-duration) and start and end time
 * 
 * @author Daniel Alm Grundström
 * @version 0.00.00
 * @name EventPopup.java
 */
public class EventPopup {
	
	private TimeSelector startTimeSelector;
	private TimeSelector endTimeSelector;
	private TextField titleField;
	private TextArea descriptionArea;
	private VBox dateAndTimeBox;
	private DatePicker startPicker;
	private DatePicker endPicker;
	private Button okayButton;
	private Button cancelButton;
	private Stage stage;
	
	public EventPopup() {	
		titleField = new TextField();
		titleField.setPrefWidth(100);
		
		descriptionArea = new TextArea();
		descriptionArea.setWrapText(true);
		descriptionArea.setPrefHeight(80);
		descriptionArea.setPrefWidth(100);

		Text titleHeader = new Text("Title");
		Text descriptionHeader = new Text("Description");
		Text eventTypeHeader = new Text("Select Event Type:");

		ToggleGroup group = new ToggleGroup();
		RadioButton durationButton = new RadioButton("Duration event");
		RadioButton nonDurationButton = new RadioButton("Non-duration event");
		durationButton.setToggleGroup(group);
		nonDurationButton.setToggleGroup(group);
		
		HBox buttons = new HBox();
		buttons.setSpacing(10);
		cancelButton = new Button("Cancel");
		okayButton = new Button("Add");
		buttons.getChildren().addAll(okayButton, cancelButton);

		dateAndTimeBox = new VBox();
		dateAndTimeBox.setPrefWidth(120);
		dateAndTimeBox.setSpacing(10);
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(titleHeader, titleField, descriptionHeader, descriptionArea, eventTypeHeader,
				durationButton, nonDurationButton, dateAndTimeBox, buttons);
		
		HBox hbox = new HBox();
		Pane filler = new Pane();
		filler.setPrefWidth(10);
		hbox.getChildren().addAll(filler, vbox);

		Group root = new Group();
		root.getChildren().addAll(hbox);

		stage = new Stage();
		stage.setScene(new Scene(root, 220, 520));
		stage.show();

		cancelButton.setOnAction(e -> {
			stage.close();
		});
		
		durationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				displayDatePickers(true);
			}
			
		});
		nonDurationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				displayDatePickers(false);
			}
			
		});
	}
	
	private void displayDatePickers(boolean duration) {
		dateAndTimeBox.getChildren().clear();
		startPicker = new DatePicker();
		Text startText = new Text();
		startTimeSelector = new TimeSelector();
		dateAndTimeBox.getChildren().addAll(startText, startPicker, startTimeSelector);
		
		if (duration) {
			startText.setText("Start Date");
			startTimeSelector.setText("Start Time");
			endTimeSelector = new TimeSelector();
			endTimeSelector.setText("End Time");
			endPicker = new DatePicker();
			dateAndTimeBox.getChildren().addAll(new Text("End Date"), endPicker, endTimeSelector);
		} else {
			startText.setText("Date");
			startTimeSelector.setText("Time");
		}
	}
	
	public void registerListener(EventPopupListener listener) {
		
	}
}