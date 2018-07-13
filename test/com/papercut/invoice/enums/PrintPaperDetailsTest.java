package com.papercut.invoice.enums;
import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;



public class PrintPaperDetailsTest {
	//A4 (.15, .25, .10, .20);
	private static final Logger logger = Logger.getLogger(PrintPaperDetailsTest.class.getName());
	
	@Test
	public void testbwPageSingleSide() {
		logger.log(Level.FINEST, "testbwPageSingleSide");
		assertEquals(.15,PrintPaperDetails.A4.bwPageSingleSide,0);
	}
	
	@Test
	public void testcolorPageSingleSide() {
		logger.log(Level.FINEST, "testcolorPageSingleSide");
		assertEquals(.25,PrintPaperDetails.A4.colorPageSingleSide,0);
	}
	
	@Test
	public void testbwPageDoubleSide() {
		logger.log(Level.FINEST, "testbwPageDoubleSide");
		assertEquals(.10,PrintPaperDetails.A4.bwPageDoubleSide,0);
	}
	
	@Test
	public void testcolorPageDoubleSide() {
		logger.log(Level.FINEST, "testcolorPageDoubleSide");
		assertEquals(.20,PrintPaperDetails.A4.colorPageDoubleSide,0);
	}

}
