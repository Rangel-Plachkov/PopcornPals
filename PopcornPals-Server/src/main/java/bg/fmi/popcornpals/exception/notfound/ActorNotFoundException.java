package bg.fmi.popcornpals.exception.notfound;

public class ActorNotFoundException extends NotFoundException {
    public ActorNotFoundException() {
        super("Actor: Actor not found");
    }
    public ActorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ActorNotFoundException(String message) {
        super(message);
    }
    public ActorNotFoundException(Throwable cause) {
        super(cause);
    }
}
