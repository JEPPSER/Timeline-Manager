package interfaces;

import model.Timeline;

/**
 * Interface used to listen to changes in the TimeLine Menu.
 * 
 * MenuController implements this interface so it can be notified whenever add
 * button, deleted button,open button, save button for a specific TimeLine is
 * pressed or a TimeLine is selected from loaded TimeLines menu .
 * 
 * 
 * Implementing classes: MenuControoler
 * 
 * 
 * @author Mustafa Alsaid and Daniel Alm Grundstrom
 * @version 0.00.00
 * @name MenuListener.java
 */

public interface MenuListener {
	
	/**
	 * This method is used by menu controller when the user press AddTimeLine
	 * button.
	 */
	public void onAddButtonClicked();

	/**
	 * This method is used by menu controller when the user press OpenTimeLine
	 * button to open a specific TimeLine
	 */
	public void onOpenButtonClicked();

	/**
	 * This method is used by menu controller when the user press DeleteTimeLine
	 * button
	 */
	public void onDeleteButtonClicked();

	/**
	 * This method is used by menu controller when the user press SaveTimeLine
	 * button to save a TimeLine
	 */
	public void onSaveButtonClicked();

	/**
	 * This method is used by menu controller when the user select a specific
	 * loaded TimeLine from the loaded TimeLine list
	 */
	public void onNewTimelineSelected(Timeline timeline);
	
	/**
	 * Called when the user clicks on any of the time perspective buttons.
	 * 
	 * @param newPerspective - the newly selected perspective, can be "Week", "Month" or "Year"
	 */
	public void onChangeTimePerspectiveClicked(String newPerspective);
	
	/**
	 * Called when the user clicks the theme button.
	 */
	public void onChangeThemeButtonClicked();
}
