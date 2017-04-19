package model;

import interfaces.ModelChangedListener;

public class TimelineContainer {

	private ModelChangedListener listener;
	
	public void addTimeline() {
		// Logic for adding timeline
		
		listener.onModelChanged(); // tell listener that a timeline has been added
	}
	
	public void registerListener(ModelChangedListener listener) {
		this.listener = listener;
	}
}
