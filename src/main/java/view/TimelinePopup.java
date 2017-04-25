package view;

import java.time.LocalDate;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A popup window that pops up when the addTimeline button is pressed. This is
 * used by the user to create a timeline by choosing a name, start and end date.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopup.java
 */
public class TimelinePopup {

	private TextField titleField;
	private LocalDate startDate;
	private LocalDate endDate;
	private DatePicker startPicker;
	private DatePicker endPicker;
	private Button saveButton;
	private Button cancelButton;

	/**
	 * Constructor that sets all the graphical components of the popup.
	 */
	public TimelinePopup() {

		startPicker = new DatePicker();
		startDate = startPicker.getValue();

		endPicker = new DatePicker();
		endDate = endPicker.getValue();

		titleField = new TextField();
		titleField.setPrefWidth(100);

		Text titleHeader = new Text("Title");
		Text startHeader = new Text("Start Date");
		Text endHeader = new Text("End Date");

		HBox buttons = new HBox();
		buttons.setSpacing(10);
		cancelButton = new Button("Cancel");
		saveButton = new Button("Save");
		buttons.getChildren().addAll(saveButton, cancelButton);

		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(titleHeader, titleField, startHeader, startPicker, endHeader, endPicker, buttons);

		HBox hbox = new HBox();
		Pane filler = new Pane();
		filler.setPrefWidth(10);
		hbox.getChildren().addAll(filler, vbox);

		Group root = new Group();
		root.getChildren().addAll(hbox);

		Stage stage = new Stage();
		stage.setScene(new Scene(root, 200, 220));
		stage.show();

		cancelButton.setOnAction(e -> {
			stage.close();
		});
	}

	/**
	 * Method for getting the start date input from the user.
	 * 
	 * @return Start Date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Method for getting the end date input from the user.
	 * 
	 * @return End Date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Method for getting the title input from the user.
	 * 
	 * @return Title of the timeline
	 */
	public String getTitle() {
		return titleField.getText();
	}

	public void registerListener() {
		saveButton.setOnAction(e -> {
			
		});
	}
}
