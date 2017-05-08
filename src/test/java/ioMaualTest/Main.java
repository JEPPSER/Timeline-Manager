package ioMaualTest;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import io.FileHandler;
import model.Event;
import model.Event.EventType;
import model.Timeline;

public class Main {

	public static void main(String[] args) throws Exception {

		Timeline timeLine = new Timeline("a");
		timeLine.setStartDate(LocalDate.now());
		timeLine.setEndDate(LocalDate.now().plusDays(1));
	
		
		

		
		timeLine.add("a", "party", LocalDateTime.now(), LocalDateTime.now().plusDays(2),Event.EventType.DURATION);
		timeLine.add("meeting", "implementation 4", LocalDateTime.now(), LocalDateTime.now().plusDays(1),Event.EventType.NON_DURATION);
		/*notice that if we add end date for a non duration event it will not be printed*/
		
		
		

		File file1 = new File(".\\src\\test\\resources\\");
		File file2 = new File(".\\src\\test\\resources\\a.xml");
		FileHandler.writeXML(timeLine, file1);
		System.out.println(FileHandler.readXML(file2));
		for(Event e:timeLine.getList())
         System.out.println(e);
		timeLine.update(timeLine.getList().get(0), "x", "party2", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), EventType.DURATION);
		FileHandler.writeXML(timeLine, file1);
		System.out.println(FileHandler.readXML(file2));
		for(Event e:timeLine.getList())
	         System.out.println(e);
		
		timeLine.delete(timeLine.getList().remove(0));
		FileHandler.writeXML(timeLine, file1);
		System.out.println(FileHandler.readXML(file2));
		for(Event e:timeLine.getList())
	         System.out.println(e);
	}
}
