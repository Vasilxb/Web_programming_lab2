package mk.ukim.finki.wp.lab.model.exception;

public class InvalidAlbumIdException extends RuntimeException {
    public InvalidAlbumIdException(Long id) {
        super(String.format("Album with id %d was not found", id));
    }
}
