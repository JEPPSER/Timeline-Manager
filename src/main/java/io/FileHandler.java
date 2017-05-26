package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

	private static final String CONFIG_PATH = System.getProperty("user.home") + "/Documents/Timeline Manager/config.properties";
	private File configFile;
	private Properties configProperties;
	
	public FileHandler() {
		configFile = new File(CONFIG_PATH);
		configProperties = new Properties();
		
		if (!configFile.getParentFile().exists()) {
			configFile.getParentFile().mkdirs();
		}
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
	
	/**
	 * Write a property to the configuration file.
	 * 
	 * @param key 
	 * @param value
	 * @param file
	 * 
	 * @throws Exception
	 */
	public void writeProperty(String key, String value) throws IOException {
		try (FileOutputStream out = new FileOutputStream(configFile)) {
			configProperties.setProperty(key, value);
			configProperties.store(out, null);
		}
	}
	
	public String readProperty(String key, String defaultVal) throws IOException {
		
		try (InputStream in = new FileInputStream(configFile)) {
			configProperties.load(in);
		}
		
		return configProperties.getProperty(key, defaultVal);
	}
}
