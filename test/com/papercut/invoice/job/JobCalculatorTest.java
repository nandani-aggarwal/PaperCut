package com.papercut.invoice.job;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.papercut.invoice.filereader.ResourceFileReader;
import com.papercut.invoice.utils.Utils;


public class JobCalculatorTest {

	private static final Logger logger = Logger.getLogger(ResourceFileReader.class.getName());
	
	public static List<Job> expectedJobsList = null;
	public static List<Job> inputJobsList = null;
	public static JobCalculator jobCalculator = null;
	public static double expectedTotalCost;

	@BeforeClass
    public static void setUpClass() {		
		jobCalculator = new JobCalculator();
		inputJobsList = new LinkedList<Job>();			
		inputJobsList.add(new Job(25,10,15, false));
		inputJobsList.add(new Job(55,13,42, true));
		inputJobsList.add(new Job(502,22,480, true));
		inputJobsList.add(new Job(1,0,1, false));
		expectedJobsList = new LinkedList<Job>();
		Job job1 = new Job(25,10,15, false);
		job1.setBwPrice(2.25);
		job1.setColorPrice(2.5);
		Job job2 = new Job(55,13,42, true);
		job2.setBwPrice(4.2);
		job2.setColorPrice(2.6);
		Job job3 = new Job(502,22,480, true);
		job3.setBwPrice(48.0);
		job3.setColorPrice(4.4);
		Job job4 = new Job(1,0,1, false);
		job4.setBwPrice(0.15);
		job4.setColorPrice(0.0);
		expectedJobsList.add(job1);
		expectedJobsList.add(job2);
		expectedJobsList.add(job3);
		expectedJobsList.add(job4);
		
		expectedTotalCost = 64.1;
    }

    @AfterClass
    public static void tearDownClass() {    	
    	expectedJobsList = null;
    }
    
	@Test
	public void testCalculateA4JobsPrice() {
		logger.log(Level.FINEST, "testCalculateA4JobsPrice");
		
		List<Job> actualjobsWithPricesList = jobCalculator.calculateA4JobsPrice(inputJobsList);
		assertEquals(expectedJobsList.get(0).getBwPrice(), actualjobsWithPricesList.get(0).getBwPrice(),0);
		assertEquals(expectedJobsList.get(0).getColorPrice(), actualjobsWithPricesList.get(0).getColorPrice(),0);
		assertEquals(expectedJobsList.get(1).getBwPrice(), actualjobsWithPricesList.get(1).getBwPrice(),0);
		assertEquals(expectedJobsList.get(1).getColorPrice(), actualjobsWithPricesList.get(1).getColorPrice(),0);
		assertEquals(expectedJobsList.get(2).getBwPrice(), actualjobsWithPricesList.get(2).getBwPrice(),0);
		assertEquals(expectedJobsList.get(2).getColorPrice(), actualjobsWithPricesList.get(2).getColorPrice(),0);
		assertEquals(expectedJobsList.get(3).getBwPrice(), actualjobsWithPricesList.get(3).getBwPrice(),0);
		assertEquals(expectedJobsList.get(3).getColorPrice(), actualjobsWithPricesList.get(3).getColorPrice(),0);
	}

	@Test
	public void testTotalCost() {
		logger.log(Level.FINEST, "testTotalCost");
		
		double actualtotalCost = jobCalculator.getTotalCost(expectedJobsList);
		assertEquals(expectedTotalCost, Utils.getDecimalFormat(actualtotalCost),0);
	}
}
