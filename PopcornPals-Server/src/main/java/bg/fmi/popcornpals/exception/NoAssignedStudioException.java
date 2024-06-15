package bg.fmi.popcornpals.exception;

public class NoAssignedStudioException extends NoContentException{
    public NoAssignedStudioException() {
        super("No studio assigned to the movie.");
    }
    public NoAssignedStudioException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoAssignedStudioException(String message) {
        super(message);
    }
    public NoAssignedStudioException(Throwable cause) {
        super(cause);
    }
}
