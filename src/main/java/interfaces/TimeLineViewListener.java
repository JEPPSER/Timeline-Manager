package interfaces;

public interface TimeLineViewListener {

	public void onAddEventClicked();
	
	public void onMouseOverEvent(String name);
	
	public void onDeleteEventClicked(String name);
	
	public void onEditEventClicked(String name);
}
