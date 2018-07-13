package com.papercut.invoice.utils;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
*This class provides various utilities like formating double value in 
*specific format and other utilities can be added later on based on requirements.
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class.getName());
	
	public static double getDecimalFormat(double value) {
		logger.log(Level.FINEST, "getDecimalFormat");
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		return Double.valueOf(decimalFormat.format(value));
	}
}
