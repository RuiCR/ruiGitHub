/**
 * 
 */
package edu.fjnu.cr.exception;


public class CustomException extends Exception{

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;

	private String message;
	
	/**
	 * 
	 */
	public CustomException() {
	}
	public CustomException(String message) {
		super(message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
