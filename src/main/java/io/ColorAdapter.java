package io;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.paint.Color;

/**
 * Adapter used to allow saving/loading Color to/from XML.
 * 
 * @author Jesper Bergstrom
 * @version 0.00.00
 * @name ColorAdapter.java
 */
public class ColorAdapter extends XmlAdapter<String, Color> {

	@Override
	public String marshal(Color color) throws Exception {
		return color.toString();
	}

	@Override
	public Color unmarshal(String string) throws Exception {
		return Color.valueOf(string);
	}	
}
