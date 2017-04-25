package ioMaualTest;

import java.io.File;

import io.FileHandler;
import model.Event;
import model.TimeLine;

public class Main {

	public static void main(String[] args) throws Exception {
		
			TimeLine timeLine = new TimeLine("a",1.1.20);
			FileHandler xml = new FileHandler();
			
			for(int i = 1; i <= 5; i++) {
				timeLine.add(new Event());
			}
			

			File file1 = new File("XML.out.xml"); 
			File file2 = new File("XML.out.out.xml"); 
			xml.writeXML(timeLine,file1 );
			System.out.println(xml.readXML(file2).toString());

		}
		}

	


