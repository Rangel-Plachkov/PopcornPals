package bg.fmi.popcornpals.exception.nocontent;

public class NoAssignedMediaException extends NoContentException {
    public NoAssignedMediaException() {
        super("No media assigned");
    }
    public NoAssignedMediaException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoAssignedMediaException(String message) {
        super(message);
    }
    public NoAssignedMediaException(Throwable cause) {
        super(cause);
    }
}
