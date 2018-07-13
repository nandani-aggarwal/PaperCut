package com.papercut.invoice.utils;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class UtilsTest {

	private static final Logger logger = Logger.getLogger(Utils.class.getName());
	
	@Test
	public void testDecimalFormat() {
		logger.log(Level.FINEST, "testDecimalFormat");
		double actualValue = Utils.getDecimalFormat(64.100000000000005);
		assertEquals(64.1,actualValue,0);
	}

}
