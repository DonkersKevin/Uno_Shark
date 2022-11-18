package be.switchfully.uno_shark.controllers.exceptions;

public class NoParkingLotByThatIdException extends RuntimeException {
    private String message;
    public NoParkingLotByThatIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
