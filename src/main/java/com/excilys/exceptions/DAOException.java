package main.java.com.excilys.exceptions;

import org.apache.log4j.Logger;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -5956106449936462146L;
	
	private Logger log = Logger.getLogger(DAOException.class);
	
	public DAOException(String message) {
        super(message);
        log.error(message);
		log.info(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        log.error(message);
		log.info(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}