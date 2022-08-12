package com.example.arrestmanagement.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(
            NoSuchClientException clientException) {
        ClientIncorrectData data = new ClientIncorrectData();
        data.setInfo(clientException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(
            ClientIncorrectException clientException){
        ClientIncorrectData data = new ClientIncorrectData();
        data.setInfo(clientException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ClientIncorrectData> handleException(
            Exception clientException) {
        ClientIncorrectData data = new ClientIncorrectData();
        data.setInfo(clientException.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }



}
