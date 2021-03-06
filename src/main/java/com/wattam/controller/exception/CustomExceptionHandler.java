package com.wattam.controller.exception;

import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // EXCEPTION HANDLERS FUNCTIONS

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {

        String[] details = ex.getLocalizedMessage().split("\n");
        ErrorResponse error = new ErrorResponse("server Error", details);

        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(RecordNotFoundException ex) {

        String[] details = ex.getLocalizedMessage().split("\n");
        ErrorResponse error = new ErrorResponse("record Not Found", details);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        String[] details = ex.getLocalizedMessage().split("\n");
        ErrorResponse error = new ErrorResponse("constraint violation", details);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String[] details = ex.getLocalizedMessage().split(";");
        ErrorResponse error = new ErrorResponse("username already exists", details);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    // OVERRIDE FUNCTIONS

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        String[] details = ex.getLocalizedMessage().split("\n");
        ErrorResponse error = new ErrorResponse("invalid format", details);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}
