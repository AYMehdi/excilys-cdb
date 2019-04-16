package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class UpdateComputerException extends Exception {

	private static final long serialVersionUID = -5934860352126194777L;

	private Logger log = Logger.getLogger(UpdateComputerException.class);
	
	public UpdateComputerException(String message) {
		log.info(message);
		log.error(message);
	}
}