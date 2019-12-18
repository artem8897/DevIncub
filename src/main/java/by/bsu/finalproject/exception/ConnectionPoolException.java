package by.bsu.finalproject.exception;

/**
 * Created for signal that an Connection pool exception of some sort has occurred.
 */

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String reason) {
        super(reason);
    }

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
