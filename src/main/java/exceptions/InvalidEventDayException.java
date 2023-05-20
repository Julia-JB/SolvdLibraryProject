package exceptions;

public class InvalidEventDayException extends IllegalArgumentException {
    public InvalidEventDayException(String message) {
        super(message);
    }
}
