package main;

import controller.MainController;
import de.jensd.fx.fontawesome.AwesomeStyle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.TimelineContainer;
import view.MainView;
import view.MenuView;

/**
 * Main class that sets up the TimelineManager to run.
 * 
 * @author Jesper Bergstrom and Zacky Kharboutli
 * @version 0.00.00
 * @name TimelineManager.java
 */
public class TimelineManager extends Application {
	Scene scene;
	private static MainView ui;
	
	/**
	 * Start method that set up the ui, TimelineContainer and Controllers.
	 */
	@Override
	public void start(Stage primaryStage) {
		ui = new MainView(primaryStage);
		TimelineContainer timelineContainer = new TimelineContainer();
		MainController mainController = new MainController(ui, timelineContainer);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		scene = new Scene(ui);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnHidden(e -> Platform.exit());

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

	public Scene getScene() {
		return scene;
	}
}
