package by.bsu.devincub.exception;

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
