package edu.khai.voloshyn.travelagency.exception;


/**
 * The type Connection Pool Exception.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class ConnectionPoolException extends Exception {
	/**
     * Instantiates a new Connection Pool exception.
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Instantiates a new Connection Pool exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection Pool exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Connection Pool exception.
     *
     * @param cause   the cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Connection Pool exception.
     *
     * @param message 				the message
     * @param cause   				the cause
     * @param enableSuppression 	enable suppression
     * @param writableStackTrace   	the writable stack trace
     */
    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
