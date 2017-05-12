package main;

import controller.MainController;
import de.jensd.fx.fontawesome.AwesomeStyle;
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

	private static MainView ui;
	
	/**
	 * Start method that set up the ui, TimelineContainer and Controllers.
	 */
	@Override
	public void start(Stage primaryStage) {

		ui = new MainView();
		TimelineContainer timelineContainer = new TimelineContainer();
		MainController mainController = new MainController(ui, timelineContainer);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		Scene scene = new Scene(ui);
		scene.getStylesheets().add(AwesomeStyle.BLUE.getStylePath());
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
}
