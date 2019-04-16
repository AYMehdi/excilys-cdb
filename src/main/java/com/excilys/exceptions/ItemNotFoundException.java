package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1773490103607918333L;

	private Logger log = Logger.getLogger(ItemNotFoundException.class);
	
	public ItemNotFoundException(String message) {
		log.info(message);
		log.error(message);
	}
}