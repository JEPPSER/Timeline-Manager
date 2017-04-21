package io;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.TimeLine;

/**
 * FileHandler class handles loading and saving a TimeLine XML file.
 * 
 * @author Mustafa Alsaid
 * @version 0.00.00
 * @name FileHandler.java
 */

public class FileHandler {
	
	private File file = new File("XML.xml"); //path of XML file.

	/**
	 * Read the contents of TimeLine XML file.
	 */
	public TimeLine readXML() throws Exception {
		JAXBContext context = JAXBContext.newInstance(TimeLine.class);
		Unmarshaller un = context.createUnmarshaller();
		return (TimeLine) un.unmarshal(file);
	}
    
	/**
	 * Create a TimeLine XML file.
	 */
	public void writeXML(TimeLine timeLine) throws Exception {
		JAXBContext context = JAXBContext.newInstance(TimeLine.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(timeLine, file);
	}

}
