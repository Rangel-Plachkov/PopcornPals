package bg.fmi.popcornpals.exception.notfound;

public class PlaylistNotFoundException extends NotFoundException {
    public PlaylistNotFoundException() {
        super("Playlist: Playlist not found");
    }
    public PlaylistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public PlaylistNotFoundException(String message) {
        super(message);
    }
    public PlaylistNotFoundException(Throwable cause) {
        super(cause);
    }
}
