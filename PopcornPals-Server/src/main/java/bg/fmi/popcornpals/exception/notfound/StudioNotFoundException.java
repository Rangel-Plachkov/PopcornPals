package bg.fmi.popcornpals.exception.notfound;

public class StudioNotFoundException extends NotFoundException {
    public StudioNotFoundException() {
        super("Studio: Studio not found");
    }
    public StudioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public StudioNotFoundException(String message) {
        super(message);
    }
    public StudioNotFoundException(Throwable cause) {
        super(cause);
    }
}
