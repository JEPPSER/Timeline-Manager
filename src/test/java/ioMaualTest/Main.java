package ioMaualTest;

import java.io.File;

import io.FileHandler;
import model.Event;
import model.Timeline;

public class Main {

	public static void main(String[] args) throws Exception {

		Timeline timeLine = new Timeline("a");
		FileHandler xml = new FileHandler();

		for (int i = 1; i <= 5; i++) {
			timeLine.add(new Event());
		}

		File file1 = new File(".\\src\\test\\resources\\");
		File file2 = new File(".\\src\\test\\resources\\XML.out.out.xml");
		xml.writeXML(timeLine, file1);
		System.out.println(xml.readXML(file2));

	}
}
