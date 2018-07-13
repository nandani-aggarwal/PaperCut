package com.papercut.invoice.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.papercut.invoice.exceptions.PaperCutException;
import com.papercut.invoice.job.Job;

/**
*
*This class validates all enteries/jobs in file and then provides jobs list as return.
*If validation fails application stops with an exception.  
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class FileValidation {

	private static final Logger logger = Logger.getLogger(FileValidation.class.getName());
	
	 /**
	   * Validates if path to file is null and if not 
	   * if that file exists and if exists if file can be openend.
	   * @param filepath path to jobfile having jobs information.
	   * @return boolean
	   * @exception PaperCutException while parsing file
	   * 
	   */
	public boolean validateIfFileExists(String filepath) throws PaperCutException {
		logger.log(Level.FINEST, "validateIfFileExists");		
		
		FileReader fileReader = null;
		try {
			 fileReader = new FileReader(filepath);
		}catch(Exception ex) {
			logger.log(Level.SEVERE, "\"File could not be found at " + filepath,ex);
			throw new PaperCutException("File could not be found at " + filepath,ex);
		}finally {
			if(fileReader != null) {
				try {
					fileReader.close();
				}catch(Exception ex) {
					logger.log(Level.SEVERE, "Error in closing file reader for file :  " + filepath,ex);
					throw new PaperCutException("Error in closing file reader for file :  " + filepath,ex);
				}
				
			}
			
		}
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(fileReader);
		}catch(Exception ex) {
			logger.log(Level.SEVERE, "Error in opening file: " + filepath,ex);
			throw new PaperCutException("Error in opening file: " + filepath,ex);
		}finally {
			if(bufferedReader != null) {				
				try {
					bufferedReader.close();
				}catch(Exception ex) {
					logger.log(Level.SEVERE, "Error in closing buffered reader for file :  " + filepath,ex);
					throw new PaperCutException("Error in closing buffered reader for file :  " + filepath,ex);
				}
			}
			
		}		
		return true;
	}
	
	 /**
	   * Validates each line or job in file is valid
	   * @param line job for which validation is to be checked
	   * @param linenumber line number of job/line passed for validation
	   * @return boolean
	   * @exception PaperCutException while parsing file
	   * 
	   */
	public boolean validateEachJob(String line,int lineNumber) throws PaperCutException {
		logger.log(Level.FINEST, "validateEachJob");
		
		if(!(line.trim().contains(ResourceFileReader.DELIMITER))) {
			logger.log(Level.SEVERE, "Job does not contain valid delimiter " + ResourceFileReader.DELIMITER + "at line number: " + lineNumber);
  		throw new PaperCutException("Job does not contain valid delimiter " + ResourceFileReader.DELIMITER + "at line number: " + lineNumber);
  	}
		String[] jobItems = line.trim().split(ResourceFileReader.DELIMITER);
		//check number of jobItems
		if(!checkIfTotalNumberOfItemsInJobValid(jobItems)) {
			logger.log(Level.SEVERE,"Number of Job Items is not "+ Job.jobItemsInFile + " at line number" + lineNumber);
			throw new PaperCutException("Number of Job Items is not "+ Job.jobItemsInFile + " at line number" + lineNumber);
			
		}
		//check if first item ie totalPages is numeric and valid number
		if(!checkIfValidNumber(jobItems[0].trim())) {
			logger.log(Level.SEVERE,"TotalPages is not valid number at line number" + lineNumber);
			throw new PaperCutException("TotalPages is not valid number at line number" + lineNumber);
			
		}
		//check if second item ie colorPages is numeric and valid number
		if(!checkIfValidNumber(jobItems[1].trim())) {
			logger.log(Level.SEVERE,"ColorPages is not valid number  at line number" + lineNumber);
			throw new PaperCutException("ColorPages is not valid number  at line number" + lineNumber);
			
		}
		//check if second item ie colorPages is numeric and valid number
		if(!checkIfTotalAndColorPagesNumberValid(jobItems[0].trim(),jobItems[1].trim())) {
			logger.log(Level.SEVERE,"Total Pages and ColorPages number is not valid number at line number" + lineNumber);
			throw new PaperCutException("Total Pages and ColorPages number is not valid number at line number" + lineNumber);
			
		}
		//check if third item ie double sided is boolean and valid string
		if(!checkIfBoolean(jobItems[2].trim())) {
			logger.log(Level.SEVERE,"Not valid boolean at line number" + lineNumber);
			throw new PaperCutException("Not valid boolean at line number" + lineNumber);
			
		}
		return true;
	}
	
	/**
	   * Validates each line or job has valid number of items
	   * @param jobItems jobItems for which validation is to be checked	  
	   * @return boolean	   
	   * 
	   */
	public boolean checkIfTotalNumberOfItemsInJobValid(String[] jobItems) {
		logger.log(Level.FINEST, "checkIfTotalNumberOfItemsInJobValid");
		if(jobItems.length == Job.jobItemsInFile) 
			return true;
		else
			return false;
	}
	
	/**
	   * Validates each if jobItem is valid number and is positive integer
	   * @param jobItem jobItems for which validation is to be checked	  
	   * @return boolean	   
	   * 
	   */
	public boolean checkIfValidNumber(String jobItem) {
		logger.log(Level.FINEST, "checkIfValidNumber");
		try {
	        int jobItemValue = Integer.parseInt(jobItem);
	        if(jobItemValue < 0) {
	        	return false;
	        }
	    } catch (NumberFormatException | NullPointerException nfe) {
	    	logger.log(Level.SEVERE,"NumberFormatException or NullPointerException for jobItem "  ,nfe);
	    	nfe.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	/**
	   * Validates if totalPages AND colorPages numbers are valid
	   * Both should be positive integer and colorpages should always be less than 
	   * or equal to totalPages
	   * @param jobTotalItem jobItems for which validation is to be checked	
	   * @param jobColorItem jobItems for which validation is to be checked	  
	   * @return boolean	   
	   * 
	   */
	public boolean checkIfTotalAndColorPagesNumberValid(String jobTotalItem, String jobColorItem) {
		logger.log(Level.FINEST, "checkIfTotalAndColorPagesNumberValid");
		try {
	        int jobTotalItemValue = Integer.parseInt(jobTotalItem);
	        int jobColorItemValue = Integer.parseInt(jobColorItem);
	        if(jobTotalItemValue < jobColorItemValue) {
	        	return false;
	        }
	    } catch (NumberFormatException | NullPointerException nfe) {
	    	logger.log(Level.SEVERE,"NumberFormatException or NullPointerException" ,nfe);
	    	nfe.printStackTrace();
	        return false;
	    }		
	    return true;
	}
	
	/**
	   * Validates each isDoubleSided is only "true" or "false"
	   * @param jobItem jobItems for which validation is to be checked	  
	   * @return boolean	   
	   * 
	   */
	public boolean checkIfBoolean(String jobItem) {
		logger.log(Level.FINEST, "checkIfBoolean");
		if (jobItem.toLowerCase().equals("true") || jobItem.toLowerCase().equals("false"))
			return true;
		else
			return false;
	}

}
