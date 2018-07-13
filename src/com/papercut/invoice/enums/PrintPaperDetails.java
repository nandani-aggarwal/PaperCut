package com.papercut.invoice.enums;

/**
*
*This enum defines constant price values for paperType Like A4 
*with its different price for different print options like 
*printing Black and white single side page , color single side page , 
*black and white double side page and color double side page.
*Similarly in future we can declare constants for other paperTypes 
*like A5.  
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public enum PrintPaperDetails {

	A4 (.15, .25, .10, .20);
	
	public final double bwPageSingleSide;
	public final double colorPageSingleSide;
	public final double bwPageDoubleSide;
	public final double colorPageDoubleSide;
		
	PrintPaperDetails(double bwPageSingleSide, double colorPageSingleSide, double bwPageDoubleSide, double colorPageDoubleSide){
		this.bwPageSingleSide = bwPageSingleSide;
	    this.colorPageSingleSide = colorPageSingleSide;
	    this.bwPageDoubleSide = bwPageDoubleSide;
	    this.colorPageDoubleSide = colorPageDoubleSide;
	}
	
	/*private double getbwSingleSide() { return bwPageSingleSide; }
	private double getcolorSingleSide() { return colorPageSingleSide; }
	private double getbwDoubleSide() { return bwPageDoubleSide; }
	private double getcolorDoubleSide() { return colorPageDoubleSide; }*/
	

}
