package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Warning window that pops up if the dates of a timeline are not set.
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name DateNullWarning.java
 */
public class DateNullWarning {
	private Button okBtn = new Button();
	private Stage stage = new Stage();
	
	public DateNullWarning(){
		BorderPane root = new BorderPane();
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		Text text = new Text("Set Dates.");
		okBtn.setText("OK");
		vbox.getChildren().addAll(text, okBtn);
		root.setCenter(vbox);
		vbox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(root, 150, 100));
		stage.show();
	}
	
	public void close(){
		okBtn.setOnAction(e -> {
			stage.close();
		});
	}
}
