package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class NoNamedComputerException extends Exception {

	private static final long serialVersionUID = 4787887810165307819L;
	
	private Logger log = Logger.getLogger(NoNamedComputerException.class);
	
	public NoNamedComputerException(String message) {
		log.error(message);
		log.info(message);
	}
}