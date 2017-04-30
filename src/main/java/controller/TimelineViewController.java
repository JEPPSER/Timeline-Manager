package controller;

/**
*@author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name TimelineViewController.java
*/

import interfaces.TimelineViewListener;
import view.EventPopup;

public class TimelineViewController implements TimelineViewListener
{
	private EventPopupController eventPopupController;
	
	@Override
	public void onAddEventClicked() 
	{
		EventPopup eventPopup = new EventPopup();
		eventPopupController = new EventPopupController();
		eventPopup.registerListener(eventPopupController);
		System.out.println("Enter event details: \nEvent name: \nEvent duration: \nEvent type: ");
	}

	@Override
	public void onMouseOverEvent(String name)
	{
		System.out.println("These are the details of your event: ");
	}

	@Override
	public void onDeleteEventClicked(String name) 
	{
		System.out.println("Are you sure you want to delete this event?");
	}

	@Override
	public void onEditEventClicked(String name) 
	{
		System.out.println("Rewrite the fields that you want edited: ");
	}
	
}
