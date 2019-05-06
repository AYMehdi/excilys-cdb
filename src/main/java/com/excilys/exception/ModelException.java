package com.excilys.exception;

public class ModelException extends Exception {

	private static final long serialVersionUID = -8634368954075301025L;
	
// ******** VARIABLE *******
	private String exceptionMessage;

// ******** CONSTRUCTOR *******
	/**
	 * Constructor with one parameter
	 * @param exceptionMessage String error message
	 */
	public ModelException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

// ******** GETTER *******
	/**
	 * Return error message
	 * @return exceptionMessage
	 */
	public String getMessage() {
		return exceptionMessage;
	}
	
// ******** SETTER *******
	/**
	 * Set the error message
	 * @param exceptionMessage String error message
	 */
	public void setMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}