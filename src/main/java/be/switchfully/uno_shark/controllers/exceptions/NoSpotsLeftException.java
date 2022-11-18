package be.switchfully.uno_shark.controllers.exceptions;

public class NoSpotsLeftException extends RuntimeException {

    private String message;
    public NoSpotsLeftException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
