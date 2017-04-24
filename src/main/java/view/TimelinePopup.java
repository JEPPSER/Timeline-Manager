package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimelinePopup {
	
	public TimelinePopup(){
		
		Group root = new Group();
		Stage stage = new Stage();
		stage.setScene(new Scene(root, 300, 300));
		stage.show();
	}
}
