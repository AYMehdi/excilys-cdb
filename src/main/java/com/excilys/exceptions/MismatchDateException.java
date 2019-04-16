package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class MismatchDateException extends Exception {

	private static final long serialVersionUID = -2175252432135653744L;
	
	private Logger log = Logger.getLogger(MismatchDateException.class);
	
	public MismatchDateException(String message) {
		log.info(message);
		log.error(message);
	}
}