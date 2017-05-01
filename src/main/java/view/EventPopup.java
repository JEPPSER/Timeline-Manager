package view;

import java.time.LocalTime;

import interfaces.EventPopupListener;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * A popup window that pops up when the add event button is pressed and when an event is edited.
 * Used by user to enter the title, description, event type (Duration or Non-duration) and start and end time
 * 
 * @author Daniel Alm Grundström
 * @version 0.00.00
 * @name EventPopup.java
 */
public class EventPopup {
	
	private static final Dimension2D POPUP_SIZE = new Dimension2D(200, 480);
	private static final int ROOT_PADDING = 10;
	private static final int DEFAULT_SPACING = 10;
	private static final int PICKER_WIDTH = 120;
	
	private final Stage stage;
	private Button okayButton;
	private TimePicker startTimeSelector;
	private TimePicker endTimeSelector;
	private TextField titleField;
	private TextArea descriptionArea;
	private VBox dateAndTimeBox;
	private DatePicker startPicker;
	private DatePicker endPicker;
	
	/**
	 * Constructor. Initializes stage and builds scene.
	 * 
	 * @param owner - The window that owns this EventPopup window
	 */
	public EventPopup(Window owner) {
		stage = new Stage();
		
		initDateAndTimeBox();

		Scene scene = new Scene(buildRoot(), POPUP_SIZE.getWidth(), POPUP_SIZE.getHeight());
		stage.setResizable(false);
		stage.setScene(scene);
		stage.initOwner(owner);
		stage.show();
	}
	
	/**
	 * Method for registering the listener for the EventPopup
	 * 
	 * @param TimelinePopupListener
	 */
	public void registerListener(EventPopupListener listener) {
		okayButton.setOnAction(e -> listener.onOkayButtonClicked(titleField.getText(), descriptionArea.getText(),
				startPicker.getValue(), startTimeSelector.getSelectedTime(), endPicker.getValue(), endTimeSelector.getSelectedTime()));
	}
	
	/**
	 * Close this EventPopup
	 */
	public void close() {
		stage.close();
	}
	
	/**
	 * Builds and returns the root pane which contains the center box with input fields and the button box 
	 * which contains the Cancel and Okay buttons
	 */
	private BorderPane buildRoot() {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(ROOT_PADDING));
		root.setBottom(buildButtonBox());
		root.setCenter(buildCenterBox());
		
		return root;
	}
	
	/**
	 * Builds and returns the center pane which contains the controls for inputting event title, description,
	 * event type, start date and time as well as end date and time
	 */
	private VBox buildCenterBox() {
		VBox center = new VBox();
		center.setSpacing(DEFAULT_SPACING);
		center.getChildren().addAll(buildTitleBox(), buildDescriptionBox(), buildEventTypeBox(), dateAndTimeBox);
		
		return center;
	}
	
	/**
	 * Builds and returns the box containing title field and the associated label
	 */
	private VBox buildTitleBox() {
		VBox titleBox = new VBox();
		
		Label titleHeader = new Label("Title");
		titleField = new TextField();
		titleField.setMaxWidth(POPUP_SIZE.getWidth() - ROOT_PADDING * 2);
		
		titleBox.getChildren().addAll(titleHeader, titleField);
		
		return titleBox;
	}
	
	/**
	 * Builds and returns the box containing description area and the associated label
	 */
	private VBox buildDescriptionBox() {
		final int DESCRIPTION_AREA_HEIGHT = 80;
		
		VBox descriptionBox = new VBox();
		
		Label descriptionHeader = new Label("Description");
		descriptionArea = new TextArea();
		descriptionArea.setWrapText(true);
		descriptionArea.setPrefHeight(DESCRIPTION_AREA_HEIGHT);
		descriptionArea.setMaxWidth(POPUP_SIZE.getWidth() - ROOT_PADDING * 2);
		
		descriptionBox.getChildren().addAll(descriptionHeader, descriptionArea);
		
		return descriptionBox;
	}
	
	/**
	 * Builds and returns the box containing radio buttons for selecting event type and the associated label
	 */
	private VBox buildEventTypeBox() {
		final int INNER_SPACING = 5;
		
		VBox outerBox = new VBox();
		VBox innerBox = new VBox();
		innerBox.setSpacing(INNER_SPACING);
		
		Label eventTypeHeader = new Label("Event Type");
		ToggleGroup group = new ToggleGroup();
		RadioButton durationButton = new RadioButton("Duration event");
		RadioButton nonDurationButton = new RadioButton("Non-duration event");
		durationButton.setToggleGroup(group);
		nonDurationButton.setToggleGroup(group);
		
		durationButton.setOnAction(e -> displayDateAndTimePickers(true));
		nonDurationButton.setOnAction(e -> displayDateAndTimePickers(false));
		
		innerBox.getChildren().addAll(durationButton, nonDurationButton);
		outerBox.getChildren().addAll(eventTypeHeader, innerBox);
		
		return outerBox;
	}
	
	/**
	 * Builds and returns the box containing the cancel and okay button
	 */
	private HBox buildButtonBox() {
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(DEFAULT_SPACING);
		
		Button cancelButton = new Button("Cancel");
		okayButton = new Button("Add");
		
		cancelButton.setOnAction(e -> close());
		
		buttons.getChildren().addAll(okayButton, cancelButton);
		
		return buttons;
	}
	
	/**
	 * Initializes the box containing the date and time picker
	 */
	private void initDateAndTimeBox() {
		dateAndTimeBox = new VBox();
		dateAndTimeBox.setPrefWidth(PICKER_WIDTH + DEFAULT_SPACING);
		dateAndTimeBox.setSpacing(DEFAULT_SPACING);
	}
	
	/**
	 * Builds and displays the date and time pickers. Called when user presses event type radio buttons
	 */
	private void displayDateAndTimePickers(boolean duration) {
		dateAndTimeBox.getChildren().clear();
		VBox startDateBox = new VBox();
		VBox startTimeBox = new VBox();
		startPicker = new DatePicker();
		startPicker.setPrefWidth(PICKER_WIDTH + DEFAULT_SPACING);
		Label startDateText = new Label("Date");
		Label startTimeText = new Label("Time");
		startTimeSelector = new TimePicker();
		
		startDateBox.getChildren().addAll(startDateText, startPicker);
		startTimeBox.getChildren().addAll(startTimeText, startTimeSelector);
		dateAndTimeBox.getChildren().addAll(startDateBox, startTimeBox);
		
		if (duration) {
			VBox endDateBox = new VBox();
			VBox endTimeBox = new VBox();
			startDateText.setText("Start Date");
			startTimeText.setText("Start Time");
			endTimeSelector = new TimePicker();
			endPicker = new DatePicker();
			endPicker.setPrefWidth(PICKER_WIDTH + DEFAULT_SPACING);
			endDateBox.getChildren().addAll(new Label("End Date"), endPicker);
			endTimeBox.getChildren().addAll(new Label("End Time"), endTimeSelector);
			dateAndTimeBox.getChildren().addAll(endDateBox, endTimeBox);
		} 
	}
	
	/**
	 * Control element that allows user to select a time in hours and minutes, between 00:00 and 23:59.
	 * 
	 * The selected time is retreived as LocalTime through getSelectedTime() 
	 * 
	 * @author Daniel Alm Grundström
	 * @version 0.00.00
	 */
	private class TimePicker extends HBox {
		private static final int HOURS_IN_DAY = 24;
		private static final int MINUTES_IN_HOUR = 60;
		
		private Spinner<Integer> hourSpinner;
		private Spinner<Integer> minuteSpinner;
		private SpinnerValueFactory<Integer> hourFactory;
		private SpinnerValueFactory<Integer> minuteFactory;
		
		private TimePicker() {
			LocalTime currentTime = LocalTime.now();
			setSpacing(DEFAULT_SPACING);
			
			hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, HOURS_IN_DAY - 1, currentTime.getHour());
			hourFactory.setWrapAround(true);
			minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, MINUTES_IN_HOUR - 1, currentTime.getMinute());
			minuteFactory.setWrapAround(true);
			
			hourSpinner = new Spinner<>();
			hourSpinner.setPrefWidth(PICKER_WIDTH / 2);
			
			minuteSpinner = new Spinner<>();
			minuteSpinner.setPrefWidth(PICKER_WIDTH / 2);
			
			hourSpinner.setValueFactory(hourFactory);
			minuteSpinner.setValueFactory(minuteFactory);
			
			getChildren().addAll(hourSpinner, minuteSpinner);
		}
		
		private LocalTime getSelectedTime() {
			return LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
		}
	}
}