package by.prokhorenko.textparser.exception;

public class FinderException extends Exception {
    public FinderException() {
    }

    public FinderException(String message) {
        super(message);
    }

    public FinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinderException(Throwable cause) {
        super(cause);
    }
}
