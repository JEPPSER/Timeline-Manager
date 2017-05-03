package ioMaualTest;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import io.FileHandler;
import model.Event;
import model.Timeline;

public class Main {

	public static void main(String[] args) throws Exception {

		Timeline timeLine = new Timeline("a");
		timeLine.setStartDate(LocalDate.now());
		timeLine.setEndDate(LocalDate.now().plusDays(1));
		FileHandler xml = new FileHandler();
		ArrayList<Event> eventList=new ArrayList<Event>();
		

		
		timeLine.add("a", "party", LocalDate.now(), LocalDate.now().plusDays(1),Event.EventType.DURATION);
		eventList.add(new Event(timeLine.getMaxId(),"x","party",LocalDate.now(),LocalDate.now().plusDays(1),Event.EventType.DURATION));
		

		File file1 = new File(".\\src\\test\\resources\\");
		File file2 = new File(".\\src\\test\\resources\\b.xml");
		xml.writeXML(timeLine, file1);
		System.out.println(xml.readXML(file2));

	}
}
