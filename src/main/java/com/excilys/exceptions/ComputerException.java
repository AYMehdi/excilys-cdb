package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class ComputerException extends Exception {

	private static final long serialVersionUID = -7126344861472380967L;

	private Logger log = Logger.getLogger(CompanyNotFoundException.class);
	
	public ComputerException(String message) {
		super(message);
		log.error(message);
		log.info(message);
	}
}