package bg.fmi.popcornpals.exception.notfound;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException() {
        super("Review: Review not found");
    }
    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ReviewNotFoundException(String message) {
        super(message);
    }
    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }
}
