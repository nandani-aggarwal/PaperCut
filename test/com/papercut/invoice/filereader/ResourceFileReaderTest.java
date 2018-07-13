package com.papercut.invoice.filereader;
import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.papercut.invoice.exceptions.PaperCutException;

public class ResourceFileReaderTest {
	
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
	public void testreadFailureFile() throws Exception {
		logger.log(Level.FINEST, "testreadSuccessFile");
		thrown.expect(PaperCutException.class);
		//check by passing not  valid file path		
		File file = new File("test/Resources/sampleFail.csv");
		String absolutePath = file.getAbsolutePath();			
		readResourceFile.readFile(absolutePath);				
	}
    
	@Test
	public void testreadSuccessFile() throws Exception {
		logger.log(Level.FINEST, "testreadSuccessFile");
		//check by passing success file path				
		File file = new File("test/Resources/sample.csv");
		String absolutePath = file.getAbsolutePath();
		readResourceFile.readFile(absolutePath);			
	}
}
