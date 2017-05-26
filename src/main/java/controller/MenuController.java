package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import de.jensd.fx.fontawesome.AwesomeStyle;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Timeline;
import model.TimelineContainer;
import view.MenuView;
import view.TimelinePopup;
import view.TimelineView;

public class MenuController implements MenuListener {
	private MenuView menuView;
	private TimelineView timelineView;
	private FileHandler fileHandler;
	private TimelineContainer timelineContainer;
	private TimelinePopupController timelinePopupController;
	private FileChooser chooser;
	private HashMap<Timeline, File> timelineFiles;
	
	/**
	 * Constructor. Initializes file handler and stores references to timeline
	 * container and menu view.
	 * 
	 * @param tc
	 *            - reference to timeline container
	 * @param mv
	 *            - reference to menu view
	 */
	public MenuController(TimelineContainer tc, MenuView mv, TimelineView tv) {
		timelineContainer = tc;
		fileHandler = new FileHandler();
		menuView = mv;
		timelineView = tv;
		timelineFiles = new HashMap<>();
	}

	@Override
	public void onAddButtonClicked() {
		System.out.println("Name timeline: \nSet duration for timeline");
		TimelinePopup popup = new TimelinePopup(menuView.getScene().getWindow());
		timelinePopupController = new TimelinePopupController(timelineContainer);
		popup.registerListener(timelinePopupController);
	}

	@Override
	public void onOpenButtonClicked() {
		File initialDirectory = new File(System.getProperty("user.home") + "/Documents/Timeline Manager/Timelines");
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("XML Files", "*.xml"),
				new ExtensionFilter("All Files", "*.*"));
		chooser.setInitialDirectory(initialDirectory);
		File file = chooser.showOpenDialog(menuView.getScene().getWindow());
		
		if (fileAlreadyOpened(file)) {
			MainController.showAlert(AlertType.INFORMATION, "The timeline you attempted to open is already opened.", ButtonType.OK);
		} else {
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
				timelineFiles.put(openedTimeline, file);
			}
		}
	}

	@Override
	public void onDeleteButtonClicked() {
		Timeline timeline = timelineContainer.getActiveTimeline();
		
		if (timeline == null) {
			MainController.showAlert(AlertType.INFORMATION, "There is no timeline to delete ", ButtonType.OK);
		} else {
			ButtonType result = MainController.showAlert(AlertType.WARNING, "Are you sure you want to delete this timeline?", ButtonType.YES, ButtonType.NO);
			
			if (result == ButtonType.YES) {
				try {
					File fileToDelete = (File)timelineFiles.get(timeline);
					Files.delete(Paths.get(fileToDelete.getPath()));
				} catch (IOException f) {
					f.printStackTrace();
				}
	
				timelineFiles.remove(timeline);
				timelineContainer.deleteTimeline();
			}
		}
	}

	@Override
	public void onSaveButtonClicked() {

		Timeline active = timelineContainer.getActiveTimeline();

		if (active == null) {
			MainController.showAlert(AlertType.INFORMATION, "There is no timeline to save ", ButtonType.OK);
		} else {
			File file = (File) timelineFiles.get(active);
			
			if (file == null) {
				File initialDirectory = new File(System.getProperty("user.home") + "/Documents/Timeline Manager/Timelines");

				// Create initial directory if it does not exist
				if (!initialDirectory.exists()) {
					initialDirectory.mkdirs();
				}

				chooser = new FileChooser();
				chooser.setInitialDirectory(initialDirectory);
				chooser.getExtensionFilters().addAll(
						new ExtensionFilter("XML Files", "*.xml"),
						new ExtensionFilter("All Files", "*.*"));
				chooser.setInitialFileName(active.getName().toLowerCase() + ".xml");
				file = chooser.showSaveDialog(menuView.getScene().getWindow());
				timelineFiles.put(active, file);
			} 
			
			try {
				fileHandler.writeXML(active, file);
				active.setHasUnsavedChanges(false);
				menuView.updateTimelineDropdown(timelineContainer.getTimelines(), active);
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
	
	@Override
	public void onChangeTimePerspectiveClicked(String newPerspective) {
		if (newPerspective.contains("Week")) {
			timelineView.setTimeline(timelineContainer.getActiveTimeline(), "Week");
		} else if (newPerspective.contains("Month")) {
			timelineView.setTimeline(timelineContainer.getActiveTimeline(), "Month");
		} else if (newPerspective.contains("Year")) {
			timelineView.setTimeline(timelineContainer.getActiveTimeline(), "Year");
		}
	}

	@Override
	public void onChangeThemeButtonClicked() {
		if (!menuView.getScene().getStylesheets().isEmpty()) {
			menuView.getScene().getStylesheets().clear();
			timelineView.setTextColor(Color.BLACK);
		} else {
			menuView.getScene().getStylesheets().add(AwesomeStyle.DARK.getStylePath());
			timelineView.setTextColor(Color.WHITE);
		}
		
		timelineView.setTimeline(timelineContainer.getActiveTimeline(), timelineView.getTimePerspective());
	}
	
	private boolean fileAlreadyOpened(File file) {
		for (File f : timelineFiles.values()) {
			if (file.toPath().equals(f.toPath())) {
				return true;
			}
		}
		
		return false;
	}
}
