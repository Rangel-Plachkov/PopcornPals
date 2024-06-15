package bg.fmi.popcornpals.exception.notfound;

public class MediaNotFoundException extends NotFoundException {
    public MediaNotFoundException() {
        super("Media: Media not found");
    }
    public MediaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public MediaNotFoundException(String message) {
        super(message);
    }
    public MediaNotFoundException(Throwable cause) {
        super(cause);
    }
}
