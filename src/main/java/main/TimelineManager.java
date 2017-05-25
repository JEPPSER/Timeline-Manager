package main;

import controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TimelineContainer;
import view.MainView;

/**
 * Main class that sets up the TimelineManager to run.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name TimelineManager.java
 */
public class TimelineManager extends Application {
	/**
	 * Start method that set up the ui, TimelineContainer and Controllers.
	 */
	@Override
	public void start(Stage primaryStage) {
		MainView ui = new MainView(primaryStage);
		TimelineContainer timelineContainer = new TimelineContainer();
		MainController mainController = new MainController(ui, timelineContainer);
		Scene scene = new Scene(ui);
	
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setOnCloseRequest(mainController::onExit);
		primaryStage.setScene(scene);
		primaryStage.show();

		mainController.setupListeners();
	}

	/**
	 * Main method that initiates the program.
	 * 
	 * @param args - application command line arguments
	 */
	public static void main(String[] args) {
		
		launch(args);	
	}
	
	public static void exit() {
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("mac")) {
			System.exit(0);
		} else {
			Platform.exit();
		}
	}
}
