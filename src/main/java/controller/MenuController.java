package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.jensd.fx.fontawesome.AwesomeStyle;
/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Timeline;
import model.TimelineContainer;
import view.MenuView;
import view.TimelinePopup;

public class MenuController implements MenuListener {
	private MenuView menuView;
	private FileHandler fileHandler;
	private TimelineContainer timelineContainer;
	private TimelinePopupController timelinePopupController;
	private DirectoryChooser chooser;

	/**
	 * Constructor. Initializes file handler and stores references to timeline
	 * container and menu view.
	 * 
	 * @param tc
	 *            - reference to timeline container
	 * @param mv
	 *            - reference to menu view
	 */
	public MenuController(TimelineContainer tc, MenuView mv) {
		timelineContainer = tc;
		fileHandler = new FileHandler();
		menuView = mv;
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
		File initialDirectory = new File(System.getProperty("user.home") + "/Documents/Timeline Manager/Timelines");
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(initialDirectory);
		File file = chooser.showOpenDialog(stage);
		if (file != null)
			System.out.println("Attempting to open timeline at location: " + file.getPath());
		Timeline openedTimeline = null;

		try {
			openedTimeline = fileHandler.readXML(file);
			openedTimeline.setHasUnsavedChanges(false);
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
		Timeline timeline = timelineContainer.getActiveTimeline();
		if (timeline == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "There is no timeline to delete ", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK) {
				alert.close();
			}
		} else {

			Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to delete this timeline?", ButtonType.YES,
					ButtonType.NO);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				String path = timeline.getPath();
				try {
					if (!path.equals(""))
						Files.delete(Paths.get(path));
				} catch (IOException f) {
					f.printStackTrace();
				}

				timelineContainer.deleteTimeline();
				alert.close();
			} else if (alert.getResult() == ButtonType.NO) {
				alert.close();
			}
		}
	}

	@Override
	public void onSaveButtonClicked(Stage stage) {

		Timeline active = timelineContainer.getActiveTimeline();

		if (active == null) {
			Alert alert = new Alert(AlertType.INFORMATION, "There is no timeline to save ", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.OK) {
				alert.close();
			}
		} else {
			File initialDirectory = new File(System.getProperty("user.home") + "/Documents/Timeline Manager/Timelines");

			// Create initial directory if it does not exist
			if (!initialDirectory.exists()) {
				initialDirectory.mkdirs();
			}

			chooser = new DirectoryChooser();
			chooser.setInitialDirectory(initialDirectory);
			File file = chooser.showDialog(stage);

			String fileName = active.getName().toLowerCase() + ".xml";
			String path = "";

			if (file != null) {
				path = file.getAbsolutePath() + "/" + fileName;
				active.setPath(path);
				System.out.println("Saving timeline to location: " + file.getPath());
			}

			try {
				fileHandler.writeXML(active, file);
				active.setHasUnsavedChanges(false);
				menuView.updateTimelineDropdown(timelineContainer.getTimelines(), timelineContainer.getActiveTimeline());
			} catch (Exception ex) {
				// TODO: Show error message in Alert window
				System.err.println("Could not save timeline. Error: " + ex.getMessage());
			}
		}
	}

	@Override
	public void onNewTimelineSelected(Timeline timeline) {
		timelineContainer.setActiveTimeline(timeline);
	}

	public TimelineContainer getTimelineContainer() {
		return timelineContainer;
	}
}
