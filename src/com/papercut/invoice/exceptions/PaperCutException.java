package com.papercut.invoice.exceptions;

/**
*
*This class acts as a custom exception handler 
*which helps to mange custom messages in case 
*of errors
*
* @author  Nandani Aggarwal
* @version 1.0
* @since   2018-07-13
*/
public class PaperCutException extends Exception {

    /**
	 * Default 
	 */
	private static final long serialVersionUID = 1L;

	public PaperCutException() {		
        super();
    }

    public PaperCutException(String message) {
        super(message);       
    }

    public PaperCutException(String message, Throwable cause) {
        super(message, cause);        
    }

}