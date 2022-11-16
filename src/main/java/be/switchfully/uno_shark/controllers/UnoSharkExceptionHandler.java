package be.switchfully.uno_shark.controllers;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class UnoSharkExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void badRequest(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

}
