package exceptions;

public class NoAvailableItemsException extends Exception {
    public NoAvailableItemsException(String message) {
        super(message);
    }
}
