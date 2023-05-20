package exceptions;

public class InvalidHoursWorkedException extends IllegalArgumentException {
    public InvalidHoursWorkedException(String message) {
        super(message);
    }
}
