package classes.common.exceptions;

public class NotBindableProperty  extends Exception {
    public NotBindableProperty() {
        super();
    }
    public NotBindableProperty(String details) {
        super(details);
    }
}