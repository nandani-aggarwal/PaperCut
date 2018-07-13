package com.papercut.invoice.codingchallenge;


import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.papercut.invoice.exceptions.PaperCutException;
import com.papercut.invoice.filereader.ResourceFileReader;
import com.papercut.invoice.job.Job;
import com.papercut.invoice.job.JobCalculator;
import com.papercut.invoice.job.JobsPrinter;

/**
*
*This class is entry point of the application
*Loads logging configuration first
*which calls file reader first to parse the resource file
*and then calls jobsCalculator to calculate prices of various jobs 
*and calls getTotal cost of all jobs and finally displays output to console
*using jobsPrinter. 
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class CodingChallengeEntry {	
	
	//Loads Initial Logging configuration
	static {
		try {
	        // Load a properties file from class path java.util.logging.config.file
	        final LogManager logManager = LogManager.getLogManager();
	        URL configURL = logManager.getClass().getResource("/logging.properties");
	        if (configURL != null) {
	            try (InputStream is = configURL.openStream()) {
	                logManager.readConfiguration(is);		               
	            }
	        }
	     } catch (Exception e) {
	    	 System.err.printf("Error while loading logging configuration from logging.properties file",e);
	        e.printStackTrace();
	      }
		
		
	}
		
	
	private static final Logger logger = Logger.getLogger(CodingChallengeEntry.class.getName());
	
	
	 /**
	   * This is the main method which makes use of various classes and methods.
	   * @param args Resource file to jobs information.
	   * @return Nothing.
	   * @exception PaperCutException while parsing file, calculating prices or displaying output.
	   * 
	   */
	public static void main(String[] args) {
 		logger.log(Level.FINEST, "testDecimalFormat");
 		
		String[] args1 = {"C:\\Users\\nandani.aggarwal\\eclipse-workspace\\PaperCut\\src\\Resources\\sample.csv"};
		
		if(args1 == null || args1.length == 0) {
			logger.log(Level.SEVERE, "Please provide path to resource file");
			System.err.println("Please provide path to resource file");
			return;
		}
		
		String filePath = args1[0];
		try {				
				
				//Read resource file
				ResourceFileReader readResourceFile = new ResourceFileReader();
				List<Job> jobsList = readResourceFile.readFile(filePath);
				
				//Calculate price of all jobs
				JobCalculator jobCalculator = new JobCalculator();
				List<Job> jobsWithPricesList = jobCalculator.calculateA4JobsPrice(jobsList);
				
				// Output the job details and job cost for each job to the console
				JobsPrinter jobsPrinter = new JobsPrinter();
				jobsPrinter.printJobsDetails(jobsWithPricesList);
				
				//Get Total Cost
				double totalCost = jobCalculator.getTotalCost(jobsWithPricesList);
				// Output Total cost to the console
				jobsPrinter.printTotalCost(totalCost);
			}catch(Exception ex) {
				logger.log(Level.SEVERE, "Exception in main",ex);
				//System.err.println(ex.getMessage());
				
			}
		
	}
	
	
}
