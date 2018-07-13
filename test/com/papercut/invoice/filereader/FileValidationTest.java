package com.papercut.invoice.filereader;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.papercut.invoice.exceptions.PaperCutException;

public class FileValidationTest {

	private static final Logger logger = Logger.getLogger(ResourceFileReaderTest.class.getName());
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	public static ResourceFileReader readResourceFile = null;
	
	@BeforeClass
    public static void setUpClass() {
		readResourceFile = new ResourceFileReader();
		
    }

    @AfterClass
    public static void tearDownClass() {
    	readResourceFile = null;
    	
    }
    

	@Test
	public void testreadWrongFile() throws Exception{
		logger.log(Level.FINEST, "testreadWrongFile");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("File could not be found at"));      
		//check by passing wrong file path		
		String filePath = "C:\\Users\\nandani.aggarwal\\Documents\\sample1.csv";	
		readResourceFile.readFile(filePath);
	}
	
	@Test
	public void testreadValidationFailsFile() throws Exception {
		logger.log(Level.FINEST, "testreadValidationFailsFile");
		thrown.expect(PaperCutException.class); 
		//check by passing not valid file	
		File file = new File("test/Resources/sampleFail.csv");
		String absolutePath = file.getAbsolutePath();		
		readResourceFile.readFile(absolutePath);
		
	}
	
	@Test
	public void testcheckIfTotalNumberOfItemsInJobValid() throws Exception {
		logger.log(Level.FINEST, "testcheckIfTotalNumberOfItemsInJobValid");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Number of Job Items is not "));      
		//check by passing not valid file
		File file = new File("test/Resources/sampleFail2.csv");
		String absolutePath = file.getAbsolutePath();			
		readResourceFile.readFile(absolutePath);
		
	}
	
	@Test
	public void testcheckIfValidNumber() throws Exception {
		logger.log(Level.FINEST, "testcheckIfValidNumber");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("not valid number"));      
		//check by passing not valid file		
		File file = new File("test/Resources/sampleFail.csv");
		String absolutePath = file.getAbsolutePath();			
		readResourceFile.readFile(absolutePath);
		
	}
	
	@Test
	public void testcheckIfBoolean() throws Exception {
		logger.log(Level.FINEST, "testcheckIfBoolean");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Not valid boolean"));      
		//check by passing not valid file		
		File file = new File("test/Resources/sampleFail3.csv");
		String absolutePath = file.getAbsolutePath();		
		readResourceFile.readFile(absolutePath);
		
	}
	
	@Test
	public void testcheckIfTotalAndColorPagesNumberValid() throws Exception {
		logger.log(Level.FINEST, "testcheckIfTotalAndColorPagesNumberValid");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Total Pages and ColorPages number is not valid number"));      
		//check by passing not valid file		
		File file = new File("test/Resources/sampleFail4.csv");
		String absolutePath = file.getAbsolutePath();		
		readResourceFile.readFile(absolutePath);
		
	}
	
	@Test
	public void testValidDelimiter() throws Exception {
		logger.log(Level.FINEST, "testValidDelimiter");
		thrown.expect(PaperCutException.class); 
		thrown.expectMessage(containsString("Job does not contain valid delimiter"));      
		//check by passing not valid file				
		File file = new File("test/Resources/sampleFail5.csv");
		String absolutePath = file.getAbsolutePath();
		readResourceFile.readFile(absolutePath);
		
	}
	

}
