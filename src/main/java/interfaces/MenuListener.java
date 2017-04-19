package interfaces;

public interface MenuListener {
	
	/*This method is used by menu controller when the user press AddTimeLine button*/
	public void onAddButtonClicked(); 
	
	/*This method is used by menu controller when the user press OpenTimeLine button
	  to open a specific TimeLine */
	public void onOpenButtonClicked();
	
	/*This method is used by menu controller when the user press DeleteTimeLine button*/
	public void onDeleteButtonClicked();
	
	/*This method is used by menu controller when the user press SaveTimeLine button to save 
	 a TimeLine*/
	public void onSaveButtonClicked();
	
	/*This method is used by menu controller when the user select a specific
	  loaded TimeLine from the loaded TiimeLine list*/
	public void onNEWTimeLineSelected(String timeLineName);
}
