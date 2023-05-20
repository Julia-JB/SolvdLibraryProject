package exceptions;

public class InvalidISBNFormatException extends NumberFormatException {
    public InvalidISBNFormatException(String message) {
        super(message);
    }
}
