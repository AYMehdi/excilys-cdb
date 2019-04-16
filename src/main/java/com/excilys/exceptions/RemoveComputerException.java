package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class RemoveComputerException extends Exception {

	private static final long serialVersionUID = 77157428816681448L;
	
	private Logger log = Logger.getLogger(RemoveComputerException.class);
	
	public RemoveComputerException(String message) {
		log.info(message);
		log.error(message);
	}
}