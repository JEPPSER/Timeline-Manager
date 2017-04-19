package controller;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeLineViewControllerTest {

	private static int test_count = 0;

	@Before
	public void setUp() {
		test_count++;
		System.out.println("Test " + test_count);
	}


	@After
	public void tearDown() {
	}

	@Test
	public void testOnAddEventClicked() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "Enter event details: \nEvent name: \nEvent duration: \nEvent type: ";
	     assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testOnMouseOverEvent() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "These are the details of your event: ";
	     assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testOnDeleteEventClicked() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
		 String expectedOutput  = "Are you sure you want to delete this event?";
		 assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void testOnEditEventClicked() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
		 String expectedOutput  = "Rewrite the fields that you want edited: ";
		 assertEquals(expectedOutput, outContent.toString());
	}

}
