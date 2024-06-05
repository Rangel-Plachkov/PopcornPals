package bg.fmi.popcornpals.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User: User not found");
    }
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
