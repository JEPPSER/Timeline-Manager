package controller;

import java.io.File;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Timeline;
import model.TimelineContainer;
import view.MenuView;
import view.TimelinePopup;

public class MenuController implements MenuListener {
	private FileHandler fileHandler;
	private TimelineContainer timelineContainer;
	private TimelinePopupController timelinePopupController;
	private DirectoryChooser chooser;

	public MenuController(TimelineContainer tc) {
		timelineContainer = tc;
		fileHandler = new FileHandler();
	}

	@Override
	public void onAddButtonClicked(MenuView menu) {
		System.out.println("Name timeline: \nSet duration for timeline");
		TimelinePopup popup = new TimelinePopup(this, menu);
		timelinePopupController = new TimelinePopupController();
		popup.registerListener(timelinePopupController);
	}

	@Override
	public void onOpenButtonClicked(Stage stage) {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(stage);
		if (file != null)
			System.out.println("Attempting to open timeline at location: " + file.getPath());
		Timeline openedTimeline = null;

		try {
			openedTimeline = fileHandler.readXML(file);
		} catch (Exception ex) {
			// TODO: Show error message in Alert window
			System.err.println("Could not open timeline. Error: " + ex.getMessage());
		}

		if (openedTimeline != null) {
			timelineContainer.addTimeline(openedTimeline);
		}
	}

	@Override
	public void onDeleteButtonClicked() {
		System.out.println("Are you sure you want to delete this timeline");
	}

	@Override
	public void onSaveButtonClicked(Stage stage) {
		
		Timeline active = timelineContainer.getActiveTimeline(); 
		
		File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\Timeline Manager\\Timelines");
		
		// Create initial directory if it does not exist
		if (!initialDirectory.exists()) {
			initialDirectory.mkdirs();
		}		
		
		chooser = new DirectoryChooser();
		chooser.setInitialDirectory(initialDirectory);
		File file = chooser.showDialog(stage);
		
		System.out.println("Saving timeline to location: " + file.getPath());

		try {
			fileHandler.writeXML(active, file);
		} catch (Exception ex) {
			// TODO: Show error message in Alert window
			System.err.println("Could not save timeline. Error: " + ex.getMessage());
		}
	}

	@Override
	public void onNEWTimeLineSelected(String timeLineName) {
		// TODO Auto-generated method stub

	}
	
	public TimelineContainer getTimelineContainer(){
		return timelineContainer;
	}
}
