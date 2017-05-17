package interfaces;

import javafx.stage.Stage;
import model.Timeline;
import view.MenuView;

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
	public void onAddButtonClicked(MenuView menu);

	/**
	 * This method is used by menu controller when the user press OpenTimeLine
	 * button to open a specific TimeLine
	 */
	public void onOpenButtonClicked(Stage stage);

	/**
	 * This method is used by menu controller when the user press DeleteTimeLine
	 * button
	 */
	public void onDeleteButtonClicked();

	/**
	 * This method is used by menu controller when the user press SaveTimeLine
	 * button to save a TimeLine
	 */
	public void onSaveButtonClicked(Stage stage);

	/**
	 * This method is used by menu controller when the user select a specific
	 * loaded TimeLine from the loaded TiimeLine list
	 */
	public void onNewTimelineSelected(Timeline timeline);
}
