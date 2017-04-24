package controller;

import java.io.File;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name MenuController.java
*/

import interfaces.MenuListener;
import io.FileHandler;
import model.TimeLine;
import model.TimelineContainer;
import view.TimelinePopup;

public class MenuController implements MenuListener
{
	private FileHandler fileHandler;
	private TimelineContainer timelineContainer;
	
	public MenuController(TimelineContainer tc) {
		timelineContainer = tc;
		fileHandler = new FileHandler();
	}
	
	@Override
	public void onAddButtonClicked() 
	{
		System.out.println("Name timeline: \nSet duration for timeline");
		TimelinePopup popup = new TimelinePopup();
		timelineContainer.addTimeline(new TimeLine());
	}

	@Override
	public void onOpenButtonClicked(File file)
	{
		System.out.println("Attempting to open timeline at location: " + file.getPath());
		TimeLine openedTimeline = null;
		
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
	public void onDeleteButtonClicked() 
	{
		System.out.println("Are you sure you want to delete this timeline");
	}

	@Override
	public void onSaveButtonClicked(File file) 
	{
		System.out.println("Saving timeline to location: " + file.getPath());
		
		TimeLine active = timelineContainer.getActiveTimeline(); // fetch the timeline to be saved
		
		try {
			fileHandler.writeXML(active, file);
		} catch (Exception ex) {
			// TODO: Show error message in Alert window
			System.err.println("Could not save timeline. Error: " + ex.getMessage());
		}
	}

	@Override
	public void onNEWTimeLineSelected(String timeLineName) 
	{
		// TODO Auto-generated method stub
		
	}

}
