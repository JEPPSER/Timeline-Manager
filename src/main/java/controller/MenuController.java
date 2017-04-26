package controller;

import java.io.File;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Timeline;
import model.TimelineContainer;
import view.TimelinePopup;

public class MenuController implements MenuListener {
	private FileHandler fileHandler;
	private TimelineContainer timelineContainer;
	private TimelinePopupController timelinePopupController;

	public MenuController(TimelineContainer tc) {
		timelineContainer = tc;
		fileHandler = new FileHandler();
	}

	@Override
	public void onAddButtonClicked() {
		System.out.println("Name timeline: \nSet duration for timeline");
		TimelinePopup popup = new TimelinePopup();
		timelinePopupController = new TimelinePopupController();
		popup.registerListener(timelinePopupController);
		timelineContainer.addTimeline(new Timeline());
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
	public void onSaveButtonClicked(File file) {
		System.out.println("Saving timeline to location: " + file.getPath());

		Timeline active = timelineContainer.getActiveTimeline(); // fetch the
																	// timeline
																	// to be
																	// saved

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

}
