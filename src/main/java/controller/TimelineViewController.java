package controller;

import interfaces.TimelineViewListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Event;
import model.TimelineContainer;
import view.EventPopup;

/**
* @author Vikrant Mainali and Tomas Mendes
* @version 0.00.00
* @name TimelineViewController.java
*/
public class TimelineViewController implements TimelineViewListener
{
	private TimelineContainer timelineContainer;
	private EventPopupController eventPopupController;
	
	public TimelineViewController(TimelineContainer tc) {
		timelineContainer = tc;
	}
	
	@Override
	public void onAddEventClicked(Stage stage) {
		EventPopup eventPopup = new EventPopup(stage);
		eventPopupController = new EventPopupController(eventPopup, timelineContainer);
		eventPopup.registerListener(eventPopupController);
		System.out.println("Enter event details: \nEvent name: \nEvent duration: \nEvent type: ");
	}
	
	@Override
	public void onEditEventClicked(Stage stage, Event event) {
		EventPopup eventPopup = new EventPopup(stage);
		
		// Initialize fields in the event popup with the information in the event that is to be edited
		eventPopup.setFields(event.getId(), event.getEventName(), event.getDescription(), event.getType(),
				event.getStartDate(), event.getEndDate(), event.getColor());
				
		
		eventPopupController = new EventPopupController(eventPopup, timelineContainer);
		eventPopup.registerListener(eventPopupController);
	}
	
	@Override
	public void onMouseOverEvent(String name) {
		System.out.println("These are the details of your event: ");
	}

	@Override
	public void onDeleteEventClicked(int eventId) {
		Alert alert = new Alert(AlertType.WARNING, String.format("Are you sure you want to delete event with ID %d?", eventId), ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		
		if(alert.getResult() == ButtonType.YES){
			timelineContainer.deleteEvent(eventId);
		}
		
		alert.close();
	}
}