package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class ComputerNotFoundException extends Exception {

	private static final long serialVersionUID = -8143772693524424868L;
	
	private Logger log = Logger.getLogger(ComputerNotFoundException.class);
	
	public ComputerNotFoundException(String message) {
		log.error(message);
		log.info(message);
	}
}
