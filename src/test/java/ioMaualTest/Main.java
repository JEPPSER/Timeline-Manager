package ioMaualTest;

import io.FileHandler;
import model.Event;
import model.TimeLine;

public class Main {

	public static void main(String[] args) throws Exception {
		
			TimeLine timeLine = new TimeLine("a");
			FileHandler xml = new FileHandler();
			
			for(int i = 1; i <= 5; i++) {
				timeLine.add(new Event());
			}
			
//			xml.writeXML(timeLine);
//			System.out.println(xml.readXML().toString());
		}
		}

	


