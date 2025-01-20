package com.job.portal.employer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = EmployerController.class)
public class EmployerControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmployerControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception
        logger.error("Internal server error", e);

        // You can customize the response message or format based on your requirements
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
    
    @ExceptionHandler(EmployerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEmployerNotFoundException(EmployerNotFoundException e) {
        // Log the exception
        logger.warn("Employer not found: {}", e.getMessage());

        // You can customize the response message or format based on your requirements
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer not found");
    }

    
}
