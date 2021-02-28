package edu.khai.voloshyn.travelagency.exception;

/**
 * The type DAO Exception.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class DAOException extends Exception {
	/**
     * Instantiates a new DAO exception.
     */
    public DAOException() {
        super();
    }

    /**
     * Instantiates a new DAO exception.
     *
     * @param message the message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new DAO exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new DAO exception.
     *
     * @param cause the cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new DAO exception.
     *
     * @param message 				the message
     * @param cause   				the cause
     * @param enableSuppression 	enable suppression
     * @param writableStackTrace   	the writable stack trace
     */
    protected DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
