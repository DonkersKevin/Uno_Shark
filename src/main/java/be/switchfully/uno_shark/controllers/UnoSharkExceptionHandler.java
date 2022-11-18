package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.controllers.exceptions.NoParkingLotByThatIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class UnoSharkExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    protected void badRequest(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(NoParkingLotByThatIdException.class)
    protected void notFound(NoParkingLotByThatIdException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(NOT_FOUND.value(), ex.getMessage());
    }
}
