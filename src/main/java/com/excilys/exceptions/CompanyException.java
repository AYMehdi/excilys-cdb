package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class CompanyException extends Exception {

	private static final long serialVersionUID = -8081879641088951531L;

	private Logger log = Logger.getLogger(CompanyNotFoundException.class);
	
	public CompanyException(String message) {
		super(message);
		log.error(message);
		log.info(message);
	}
}