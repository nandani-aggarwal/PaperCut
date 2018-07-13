package com.papercut.invoice.job;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.papercut.invoice.utils.Utils;


/**
*
*This class acts as a bean for all jobs
*It provides functionality of displaying all jobs to the console.  
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class Job {
	
	private static final Logger logger = Logger.getLogger(Job.class.getName());
	
	private int totalPages;//Number of total pages in a job
	private int colourPages;//Number of colored pages in a job
	private int bwPages;//Number of black and white pages in a job
	private boolean isDoubleSided;//Is job involved single side or double side printing
	private double colorPrice;//Price of all colored page printing in a job
	private double bwPrice;//Price of all black and white page printing in a job
	
	public static final int jobItemsInFile = 3;//Number of columns or jobitems in resource file
	
	public Job(int totalPages,int colourPages,int bwPages,Boolean isDoubleSided) {
		logger.log(Level.FINEST, "Job");
		this.totalPages = totalPages;
		this.colourPages = colourPages;
		this.bwPages = bwPages;
		this.isDoubleSided = isDoubleSided;
		this.colorPrice = 0.0;
		this.bwPrice = 0.0;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getColourPages() {
		return colourPages;
	}
	public void setColourPages(int colourPages) {
		this.colourPages = colourPages;
	}
	public int getBwPages() {
		return bwPages;
	}

	public void setBwPages(int bwPages) {
		this.bwPages = bwPages;
	}

	
	public boolean isDoubleSided() {
		return isDoubleSided;
	}

	public void setDoubleSided(boolean isDoubleSided) {
		this.isDoubleSided = isDoubleSided;
	}

	public double getColorPrice() {
		return colorPrice;
	}

	public void setColorPrice(double colorPrice) {
		this.colorPrice = colorPrice;
	}

	public double getBwPrice() {
		return bwPrice;
	}

	public void setBwPrice(double bwPrice) {
		this.bwPrice = bwPrice;
	}

	public static void printJob(Job job) {
		logger.log(Level.FINEST, "printJob");
		System.out.println("*********************************************");
		System.out.println("     Total Pages : " + job.getTotalPages());
		System.out.println("       B/W Pages : " + job.getBwPages());
		System.out.println("     Color Pages : " + job.getColourPages());
		System.out.println("    Double Sided : " + job.isDoubleSided());
		System.out.println("  B/W Price(AUD) : " + Utils.getDecimalFormat(job.getBwPrice()));
		System.out.println("Color Price(AUD) : " + Utils.getDecimalFormat(job.getColorPrice()));		
		System.out.println("Total Price(AUD) : " + Utils.getDecimalFormat(job.getBwPrice() + job.getColorPrice()));
		System.out.println("*********************************************");
		
	}

}
