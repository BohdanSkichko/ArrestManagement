package com.example.arrestmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArrestGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ArrestResponse> handleException(
            NoSuchArrestException arrestException) {
        ArrestResponse data = new ArrestResponse();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ArrestResponse> handleException(
            ArrestIncorrectException arrestException) {
        ArrestResponse data = new ArrestResponse();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ArrestResponse> handleException(
            Exception arrestException) {
        ArrestResponse data = new ArrestResponse();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
