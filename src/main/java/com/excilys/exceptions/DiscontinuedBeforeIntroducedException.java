package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class DiscontinuedBeforeIntroducedException extends Exception {

	private static final long serialVersionUID = 6129995409280655597L;

	private Logger log = Logger.getLogger(DiscontinuedBeforeIntroducedException.class);
	
	public DiscontinuedBeforeIntroducedException(String message) {
		log.info(message);
		log.error(message);
	}
}