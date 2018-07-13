package com.papercut.invoice.job;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.papercut.invoice.utils.Utils;


public class JobTest {

	private static final Logger logger = Logger.getLogger(JobTest.class.getName());
		
	private static OutputStream outputStream = null;	
	private static final String LINE_SEPERATOR = System.getProperty("line.separator");
	public static Job job = null;
	
	@BeforeClass
    public static void setUpClass() {		
		outputStream = new ByteArrayOutputStream();
		job = new Job(25,10,15, false);
		job.setBwPrice(2.25);
		job.setColorPrice(2.5);
		
    }

    @AfterClass
    public static void tearDownClass() {    	
    	job = null;
    	outputStream = null;
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }
	
	@Test
	public void testPrintJob() {
		logger.log(Level.FINEST, "testPrintJob");
		
		Job.printJob(job);
		
		StringBuilder builder = new StringBuilder(); 
		builder.append("*********************************************").append(LINE_SEPERATOR);
		builder.append("     Total Pages : " + job.getTotalPages()).append(LINE_SEPERATOR);
		builder.append("       B/W Pages : " + job.getBwPages()).append(LINE_SEPERATOR);
		builder.append("     Color Pages : " + job.getColourPages()).append(LINE_SEPERATOR);
		builder.append("    Double Sided : " + job.isDoubleSided()).append(LINE_SEPERATOR);
		builder.append("  B/W Price(AUD) : " + Utils.getDecimalFormat(job.getBwPrice())).append(LINE_SEPERATOR);
		builder.append("Color Price(AUD) : " + Utils.getDecimalFormat(job.getColorPrice())).append(LINE_SEPERATOR);		
		builder.append("Total Price(AUD) : " + Utils.getDecimalFormat(job.getBwPrice() + job.getColorPrice())).append(LINE_SEPERATOR);
		builder.append("*********************************************").append(LINE_SEPERATOR);
		
		assertEquals(builder.toString(), outputStream.toString());
	}

}
