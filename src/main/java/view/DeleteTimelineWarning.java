package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Popup window with a warning, asking if you really want to delete this timeline.
 * @author Jesper Bergstrom
 */
public class DeleteTimelineWarning {
	
	private Button yesBtn = new Button();
	private Button noBtn = new Button();
	private Stage stage = new Stage();
	
	public DeleteTimelineWarning(){
		BorderPane root = new BorderPane();
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		Text text = new Text("Are you sure you want to\n delete this timeline?");
		yesBtn.setText("Yes");
		noBtn.setText("No");
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(5);
		hbox.getChildren().addAll(yesBtn, noBtn);
		vbox.getChildren().addAll(text, hbox);
		root.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(root, 150, 100));
		stage.show();
	}
	
	/**
	 * Method for getting the Yes Button.
	 * @return Yes Button
	 */
	public Button getYesButton(){
		return yesBtn;
	}
	
	/**
	 * Method for getting the No Button.
	 * @return No Button
	 */
	public Button getNoButton(){
		return noBtn;
	}
	
	/**
	 * Method for getting the Stage.
	 * @return Stage
	 */
	public Stage getStage(){
		return stage;
	}
}
