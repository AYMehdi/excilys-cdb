package com.excilys.exception;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1374678074930075617L;
	
// ******** VARIABLE *******
	private String exceptionMessage;

// ******** CONSTRUCTOR *******
	/**
	 * Constructor with one parameter
	 * @param exceptionMessage String error message
	 */
	public DAOException(String exceptionMessage) {
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