package by.javatr.finance.dao.exception;

public class InvalidFieldException extends Exception {
    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
