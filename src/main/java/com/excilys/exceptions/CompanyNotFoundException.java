package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class CompanyNotFoundException extends Exception {

	private static final long serialVersionUID = -681487484073447161L;
	
	private Logger log = Logger.getLogger(CompanyNotFoundException.class);
	
	public CompanyNotFoundException(String message) {
		log.error(message);
		log.info(message);
	}
}