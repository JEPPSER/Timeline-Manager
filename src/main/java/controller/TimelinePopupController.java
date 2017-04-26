package controller;

import java.io.File;
import java.time.LocalDate;

import interfaces.TimelinePopupListener;
import io.FileHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.Timeline;
import view.DateNullWarning;
import view.DateWarning;
import view.TitleWarning;

/**
 * Controller that handles all actions made by the user in the TimelinePopup.
 * The purpose of the TimelinePopup is to create a timeline.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name TimelinePopupController.java
 */
public class TimelinePopupController implements TimelinePopupListener {

	FileHandler handler;
	DirectoryChooser chooser;

	@Override
	public void onSaveButtonClicked(String title, LocalDate startDate, LocalDate endDate, Stage stage) {
		handler = new FileHandler();

		if (title.equals("")) {
			TitleWarning warning = new TitleWarning();
			warning.close();
		} else if(startDate == null || endDate == null){
			DateNullWarning warning = new DateNullWarning();
			warning.close();
		} else if (startDate.isAfter(endDate)) {
			DateWarning warning = new DateWarning();
			warning.close();
		} else {
			Timeline timeline = new Timeline();
			timeline.setEndDate(endDate);
			timeline.setStartDate(startDate);
			timeline.setName(title);

			File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\Timeline Manager\\Timelines");
			
			// Create initial directory if it does not exist
			if (!initialDirectory.exists()) {
				initialDirectory.mkdirs();
			}
			
			chooser = new DirectoryChooser();
			chooser.setInitialDirectory(initialDirectory);

			try {
				File file = chooser.showDialog(stage);
				if (file != null)
					handler.writeXML(timeline, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
		}
	}
}
