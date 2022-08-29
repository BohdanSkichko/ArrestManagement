package com.example.arrestmanagement.exception.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArrestGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ArrestIncorrectData> handleException(
            NoSuchArrestException arrestException) {
        ArrestIncorrectData data = new ArrestIncorrectData();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ArrestIncorrectData> handleException(
            ArrestIncorrectException arrestException){
        ArrestIncorrectData data = new ArrestIncorrectData();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ArrestIncorrectData> handleException(
            Exception arrestException) {
        ArrestIncorrectData data = new ArrestIncorrectData();
        data.setInfo(arrestException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
