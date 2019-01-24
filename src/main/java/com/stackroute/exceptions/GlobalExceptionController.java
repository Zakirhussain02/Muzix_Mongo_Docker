package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MuzixAlreadyExistsException.class)
    public ResponseEntity handleMuzixFoundException (final MuzixAlreadyExistsException a){
        return new ResponseEntity(a.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MuzixTrackNotFoundException.class)
    public ResponseEntity handleMuzixNotFoundException (final MuzixTrackNotFoundException a){
        return new ResponseEntity(a.getMessage(), HttpStatus.CONFLICT);
    }
}
