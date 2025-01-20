package com.job.portal.employer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        // You can log the exception here
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCustomerNotFoundException(EmployerNotFoundException e) {
        // Log the exception or perform any other necessary actions
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}