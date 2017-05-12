package view;

import controller.MenuController;
import interfaces.TimelinePopupListener;
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
	private DatePicker startPicker;
	private DatePicker endPicker;
	private Button saveButton;
	private Button cancelButton;
	private Stage stage;
	private MenuController controller;
	private MenuView menu;

	/**
	 * Constructor that sets all the graphical components of the popup.
	 */
	public TimelinePopup(MenuController controller, MenuView menu) {

		this.controller = controller;
		this.menu = menu;
		
		startPicker = new DatePicker();
		endPicker = new DatePicker();

		titleField = new TextField();
		titleField.setPrefWidth(100);

		Text titleHeader = new Text("Title");
		Text startHeader = new Text("Start Date");
		Text endHeader = new Text("End Date");

		HBox buttons = new HBox();
		buttons.setSpacing(10);
		cancelButton = new Button("Cancel");
		saveButton = new Button("Add");
		buttons.getChildren().addAll(saveButton, cancelButton);

		VBox vbox = new VBox();
		vbox.setSpacing(8);
		vbox.getChildren().addAll(titleHeader, titleField, startHeader, startPicker, endHeader, endPicker, buttons);

		HBox hbox = new HBox();
		Pane filler = new Pane();
		filler.setPrefWidth(10);
		hbox.getChildren().addAll(filler, vbox);

		Group root = new Group();
		root.getChildren().addAll(hbox);

		stage = new Stage();
		stage.setScene(new Scene(root, 220, 225));
		stage.setResizable(false);
		stage.show();

		cancelButton.setOnAction(e -> {
			stage.close();
		});
	}

	/**
	 * Method for registering the listener for the TimelinePopup
	 * 
	 * @param TimelinePopupListener
	 */
	public void registerListener(TimelinePopupListener listener) {
		saveButton.setOnAction(e -> {
			listener.onSaveButtonClicked(titleField.getText(),
					startPicker.getValue(),
						endPicker.getValue(),
							stage, controller,
								menu);
		});
	}
}
