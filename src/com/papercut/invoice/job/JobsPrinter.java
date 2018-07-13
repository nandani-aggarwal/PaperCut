package com.papercut.invoice.job;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.papercut.invoice.utils.Utils;

/**
*
*This class acts as a displayer and provides functionality to print all jobs 
*with its price and other details to console.It also displays total cost to console. 
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class JobsPrinter {

	private static final Logger logger = Logger.getLogger(JobsPrinter.class.getName());
	
	/**
	   * Displays all job details with its prices to console
	   * @param jobsList list of jobs
	   * @return Nothing	   
	   * 
	   */
	public void printJobsDetails(List<Job> jobsWithPricesList) {
		logger.log(Level.FINEST, "printJobsDetails");
		
		System.out.println("Job Details with costs are:");
		double totalCost = 0.0;
		for (Job job : jobsWithPricesList) {
			Job.printJob(job);
			totalCost += job.getBwPrice() + job.getColorPrice();
		}
		
		System.out.println("");
	
	}
	
	/**
	   * Displays total cost of all jobs to console
	   * @param jobsList list of jobs
	   * @return Nothing	   
	   * 
	   */
	public void printTotalCost(double totalCost) {
		logger.log(Level.FINEST, "printTotalCost");
		
		System.out.println("=============================================");
		System.out.println("Total cost of all jobs is AUD " + Utils.getDecimalFormat(totalCost));
		System.out.println("=============================================");
	}
}
