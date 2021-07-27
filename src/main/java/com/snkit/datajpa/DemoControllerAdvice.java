package com.snkit.datajpa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.ControllerAdvice
public class DemoControllerAdvice {

    @ExceptionHandler(value
            = { Exception.class })
    protected ResponseEntity<String> handleConflict(Exception ex, WebRequest request) {
        String bodyOfResponse = "Insufficient privilege  from controller advice ";

        return new ResponseEntity<String>(bodyOfResponse,HttpStatus.OK);
    }
}
