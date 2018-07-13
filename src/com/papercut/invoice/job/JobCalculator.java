package com.papercut.invoice.job;


import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.papercut.invoice.enums.PrintPaperDetails;
import com.papercut.invoice.exceptions.PaperCutException;

/**
*
*This class calculates price of all A4 jobs till now and in future we can add 
*calculation for other page Types as well according to requirement
*This also calculates total cost of all jobs. 
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class JobCalculator {
	private static final Logger logger = Logger.getLogger(JobCalculator.class.getName());
	
	 /**
	   * Calculates price of all jobs in list
	   * @param jobsList list of jobs
	   * @return List with updated price of each job	   
	   * 
	   */
	public List<Job> calculateA4JobsPrice(List<Job> jobsList) {
		logger.log(Level.FINEST, "calculateJobsPrice");
		List<Job> updatedJobsPriceList = new LinkedList<Job>();
		for (Job job : jobsList) {
			double colorPrice = 0.0;
			double bwPrice = 0.0;
			if(job.isDoubleSided()) {
				colorPrice = job.getColourPages() * PrintPaperDetails.A4.colorPageDoubleSide;
				bwPrice = job.getBwPages() * PrintPaperDetails.A4.bwPageDoubleSide;
			}else {
				colorPrice = job.getColourPages() * PrintPaperDetails.A4.colorPageSingleSide;
				bwPrice = job.getBwPages() * PrintPaperDetails.A4.bwPageSingleSide;
			}
			job.setBwPrice(bwPrice);
			job.setColorPrice(colorPrice);			
			updatedJobsPriceList.add(job);
		}
		
		return updatedJobsPriceList;
		
	}
	
	/**
	   * Calculates total price of all jobs in list
	   * @param jobsList list of jobs
	   * @return double TotalCost of all jobs	   
	   * 
	   */
	public double getTotalCost(List<Job> jobsWithPricesList) {
		logger.log(Level.FINEST, "getTotalCost");
		double totalCost = 0.0;
		for (Job job : jobsWithPricesList) {			
			totalCost += job.getBwPrice() + job.getColorPrice();
		}
		
		return totalCost;
	}

}
