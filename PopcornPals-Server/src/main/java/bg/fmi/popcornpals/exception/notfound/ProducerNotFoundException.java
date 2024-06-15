package bg.fmi.popcornpals.exception.notfound;

public class ProducerNotFoundException extends NotFoundException {
    public ProducerNotFoundException() {
        super("Producer: Producer not found");
    }
    public ProducerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProducerNotFoundException(String message) {
        super(message);
    }
    public ProducerNotFoundException(Throwable cause) {
        super(cause);
    }
}
