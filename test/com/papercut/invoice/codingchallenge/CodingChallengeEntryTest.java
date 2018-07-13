package com.papercut.invoice.codingchallenge;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.papercut.invoice.exceptions.PaperCutException;


public class CodingChallengeEntryTest {

	private static final Logger logger = Logger.getLogger(CodingChallengeEntryTest.class.getName());
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/***
	 * Read file successfully so no exception
	 */
	@Test
	public void testSuccessMain() {
		logger.log(Level.FINEST, "testSuccessMain");
		     
		//check by passing valid file	
		File file = new File("src/Resources/sample.csv");
		String[] absolutePath = {file.getAbsolutePath()};			
		CodingChallengeEntry.main(absolutePath);
		
	}
	/***
	 * 
	 * Handled Exception in main in case null or no argument is passed
	 */
	  @Test
		public void testreadNullFileMain() throws Exception {
	    	logger.log(Level.FINEST, "testreadNullFile");			
			//Check by passing null
			String[] filePath = null;	
			CodingChallengeEntry.main(filePath);
					
		}
	
	/***
	 * 
	 * Handled Exception in main in case wrong file is passed
	 */
	@Test
	public void testFailMain() {
		logger.log(Level.FINEST, "testFailMain");
		//thrown.expect(Exception.class); 
		//check by passing not valid file	
		File file = new File("test/Resources/sampleFail.csv");
		String[] absolutePath = {file.getAbsolutePath()};			
		CodingChallengeEntry.main(absolutePath);
		
	}

}
