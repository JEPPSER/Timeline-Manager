package view;

import java.text.DecimalFormat;
import java.time.LocalTime;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TimeSelector extends VBox {
	private Label label;
	private Spinner<Integer> hourSpinner;
	private Spinner<Integer> minuteSpinner;
	private SpinnerValueFactory<Integer> hourFactory;
	private SpinnerValueFactory<Integer> minuteFactory;
	
	public TimeSelector() {
		label = new Label();
		LocalTime currentTime = LocalTime.now();
		
		HBox spinnerBox = new HBox();
		spinnerBox.setSpacing(10);
		hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, currentTime.getHour());
		hourFactory.setWrapAround(true);
		minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, currentTime.getMinute());
		minuteFactory.setWrapAround(true);
		
		hourSpinner = new Spinner<>();
		minuteSpinner = new Spinner<>();
		
		hourSpinner.setValueFactory(hourFactory);
		minuteSpinner.setValueFactory(minuteFactory);
		
		spinnerBox.getChildren().addAll(hourSpinner, minuteSpinner);
		getChildren().addAll(label, spinnerBox);
	}
	
	public LocalTime getSelectedTime() {
		return LocalTime.of(hourSpinner.getValue(), minuteSpinner.getValue());
	}
	
	public void setText(String text) {
		label.setText(text);
	}
}
