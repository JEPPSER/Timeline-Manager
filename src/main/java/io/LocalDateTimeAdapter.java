package io;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class LocalDateTimeAdapter  extends XmlAdapter<String, LocalDateTime>{
	
	
	/**
	 * This class is an adapter to be able to save and load the start and end date of an event in XML.
	 * 
	 * @author Mustafa Alsaid
	 * @version 0.00.00
	 * @name FileHandler.java
	 */

	    public LocalDateTime unmarshal(String v) throws Exception {
	        return LocalDateTime.parse(v);
	    }

	    public String marshal(LocalDateTime v) throws Exception {
	        return v.toString();
	    }
	}

