package classes.common.exceptions;

public class MethodsDontMatchException extends Exception {
    public MethodsDontMatchException() {
        super();
    }
    public MethodsDontMatchException(String details) {
        super(details);
    }
}
