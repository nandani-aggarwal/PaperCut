package com.papercut.invoice.job;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.papercut.invoice.utils.Utils;



public class JobsPrinterTest {

	private static final Logger logger = Logger.getLogger(JobsPrinterTest.class.getName());
	private static OutputStream outputStream = null;	
	private static final String LINE_SEPERATOR = System.getProperty("line.separator");
	private static JobsPrinter jobsPrinter = null;
	
	 @BeforeClass
    public static void setUpClass() {
		 outputStream = new ByteArrayOutputStream();
		 jobsPrinter = new JobsPrinter();
    }

    @AfterClass
    public static void tearDownClass() {
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
	public void testPrintTotalCost() {
		logger.log(Level.FINEST, "testPrintTotalCost");
		double totalCost = 64.1;
        jobsPrinter.printTotalCost(totalCost);
        
        StringBuilder builder = new StringBuilder();  
        
        builder.append("=============================================").append(LINE_SEPERATOR);
        builder.append("Total cost of all jobs is AUD " + Utils.getDecimalFormat(totalCost)).append(LINE_SEPERATOR);
        builder.append("=============================================").append(LINE_SEPERATOR);
	
        assertEquals(builder.toString(), outputStream.toString());
	}

}
