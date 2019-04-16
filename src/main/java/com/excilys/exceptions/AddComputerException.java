package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class AddComputerException extends Exception {

	private static final long serialVersionUID = -5271354949061046463L;
	
	private Logger log = Logger.getLogger(CompanyNotFoundException.class);
	
	public AddComputerException(String message) {
		log.error(message);
		log.info(message);
	}
}