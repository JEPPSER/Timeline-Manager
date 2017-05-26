package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Timeline;

/**
 * FileHandler class handles loading and saving a TimeLine XML file.
 * 
 * @author Mustafa Alsaid
 * @version 0.00.00
 * @name FileHandler.java
 */

public class FileHandler {

	private Properties config;
	
	public FileHandler() {
		config = new Properties();
	}
	
	/**
	 * Read the contents of TimeLine XML file.
	 */
	public Timeline readXML(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Timeline.class);
		Unmarshaller un = context.createUnmarshaller();
		return (Timeline) un.unmarshal(file);
	}

	/**
	 * Create a TimeLine XML file.
	 */
	public void writeXML(Timeline timeLine, File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Timeline.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(timeLine, file);
	}
	
	public void writeProperty(String key, String value, File file) throws Exception {
		FileOutputStream out = new FileOutputStream(file);
		config.setProperty(key, value);
		config.store(out, null);
		out.close();
	}
	
	public String readProperty(String key, File file) throws Exception {
		InputStream in = new FileInputStream(file);
		
		config.load(in);
		String val = config.getProperty(key);
		in.close();
		
		return val;
	}
}
