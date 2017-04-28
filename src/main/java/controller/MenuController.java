package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Timeline;
import model.TimelineContainer;
import view.DeleteTimelineWarning;
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
		} catch (Exception ex) {
			// TODO: Show error message in Alert window
			System.err.println("Could not open timeline. Error: " + ex.getMessage());
		}

		if (openedTimeline != null) {
			timelineContainer.addTimeline(openedTimeline);
			final Timeline selectedTimeline = openedTimeline;
			MenuItem item = new MenuItem();
			item.setText(openedTimeline.getName());
			menuView.getLoadedTimelines().setText(openedTimeline.getName());
			menuView.getLoadedTimelines().getItems().add(item);
			item.setOnAction(e -> {
				menuView.getLoadedTimelines().setText(item.getText());
				timelineContainer.setActiveTimeline(selectedTimeline);
			});
		}
	}

	@Override
	public void onDeleteButtonClicked() {
		System.out.println("Are you sure you want to delete this timeline");
		DeleteTimelineWarning warning = new DeleteTimelineWarning();

		warning.getYesButton().setOnAction(e -> { // Yes button was pressed.

			Timeline timeline = timelineContainer.getActiveTimeline();
			String path = timeline.getPath();
			try {
				if (!path.equals(""))
					Files.delete(Paths.get(path));
			} catch (IOException f) {
				f.printStackTrace();
			}

			// Finding the right menu item and removing it.
			for (int i = 0; i < menuView.getLoadedTimelines().getItems().size(); i++) {
				if (menuView.getLoadedTimelines().getItems().get(i).getText().equals(timeline.getName())) {
					menuView.getLoadedTimelines().getItems().remove(i);
				}
			}

			menuView.getLoadedTimelines().setText("Timelines");
			timelineContainer.getTimelines().remove(timelineContainer.getActiveTimeline());
			timelineContainer.setActiveTimeline(null);
			warning.getStage().close();
		});

		warning.getNoButton().setOnAction(e -> { // No button was pressed.
			warning.getStage().close();
		});
	}

	@Override
	public void onSaveButtonClicked(Stage stage) {

		Timeline active = timelineContainer.getActiveTimeline();

		File initialDirectory = new File(System.getProperty("user.home") + "/Documents/Timeline Manager/Timelines");

		// Create initial directory if it does not exist
		if (!initialDirectory.exists()) {
			initialDirectory.mkdirs();
		}

		chooser = new DirectoryChooser();
		chooser.setInitialDirectory(initialDirectory);
		File file = chooser.showDialog(stage);

		String fileName = active.getName().toLowerCase() + ".xml";
		String path = file.getAbsolutePath() + "/" + fileName;
		active.setPath(path);

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

	public TimelineContainer getTimelineContainer() {
		return timelineContainer;
	}
}
