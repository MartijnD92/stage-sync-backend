package nl.stagesync.stagesync.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}