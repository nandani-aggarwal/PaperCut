package com.papercut.invoice.exceptions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.papercut.invoice.codingchallenge.CodingChallengeEntry;
import com.papercut.invoice.filereader.ResourceFileReader;

public class PaperCutExceptionTest {

	private static final Logger logger = Logger.getLogger(CodingChallengeEntry.class.getName());
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testPaperCutException() throws PaperCutException {
		logger.log(Level.FINEST, "readFile");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Testing Papercut Exceptions"));   
		if(true) {
			throw new PaperCutException("Testing Papercut Exceptions");
		}
	}

	@Test
	public void testPaperCutThrowCauseException() throws PaperCutException {
		logger.log(Level.FINEST, "readFile");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Error in File Reader"));     
		ResourceFileReader readResourceFile = new ResourceFileReader();
		//check by passing not valid file	
		try {
			File file = new File("test/Resources/sampleFailTest.csv");
			String absolutePath = file.getAbsolutePath();		
			readResourceFile.readFile(absolutePath);
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Error in File Reader",e);
			throw new PaperCutException("Error in File Reader",e);
		}
		
	}
}
