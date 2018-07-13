package com.papercut.invoice.filereader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.papercut.invoice.exceptions.PaperCutException;
import com.papercut.invoice.job.Job;

/**
*
*This class reads the resource file provided by path given as argument to application.
*This class on successful validation of file provides jobs list as return.
*If validation fails application stops with an exception.  
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class ResourceFileReader {
	
	private static final Logger logger = Logger.getLogger(ResourceFileReader.class.getName());
	public static final String DELIMITER = ",";
	
	 /**
	   * This is the main method which makes use of various classes and methods.
	   * @param filepath path to jobfile having jobs information.
	   * @return List of all jobs details.
	   * @exception PaperCutException while parsing file
	   * 
	   */
	public List<Job> readFile(String filepath) throws Exception{
		logger.log(Level.FINEST, "readFile");
		List<Job> jobsList = new LinkedList<Job>();
		
		FileValidation fileValidation = new FileValidation();
		
		boolean isFileExists = fileValidation.validateIfFileExists(filepath);
		
		if(isFileExists) {			
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
		    
			try {
				fileReader = new FileReader(filepath); 
				bufferedReader = new BufferedReader(fileReader);
			
			    String line = null; 
			    bufferedReader.readLine();
			    int lineNumber = 1;
			    //reads file until there are no more lines, using a null check
			    while( (line = bufferedReader.readLine()) != null ){			    	
			    	lineNumber += 1;
			    	if(fileValidation.validateEachJob(line,lineNumber)) {
			    		String[] jobItems = line.trim().split(DELIMITER);
			    		int totalPages = Integer.parseInt(jobItems[0].trim());
			    		int colorPages = Integer.parseInt(jobItems[1].trim());
			    		boolean isDoubleSided = Boolean.parseBoolean(jobItems[2].trim());
			    		Job paperJob = new Job(totalPages,colorPages,totalPages - colorPages,isDoubleSided);		    		
			    		jobsList.add(paperJob);
			    	}
			    }
			}
			finally {
				 fileReader.close();
				 bufferedReader.close();
			}
		      
		}
		return jobsList;
	}
	
	
}
