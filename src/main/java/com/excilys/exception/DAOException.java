package main.java.com.excilys.exception;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -1788911503996478064L;

	public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}