package by.prokhorenko.textparser.exception;

public class SorterException extends Exception {
    public SorterException() {
    }

    public SorterException(String message) {
        super(message);
    }

    public SorterException(String message, Throwable cause) {
        super(message, cause);
    }

    public SorterException(Throwable cause) {
        super(cause);
    }
}
