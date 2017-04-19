package controller;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MenuControllerTest {
	
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
	public void testOnAddButtonClicked() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "Name timeline: \nSet duration for timeline";
	     assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testOnOpenButtonClicled() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "Select the timeline you want to launch";
	     assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testOnDeleteButtonClicled() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "Are you sure you want to delete this timeline";
	     assertEquals(expectedOutput, outContent.toString());

	}

	@Test
	public void testOnSaveButtonClicled() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     String expectedOutput  = "Browse to the location where you want to save the timeline";
	     assertEquals(expectedOutput, outContent.toString());

	}

}
