package com.job.portal.jobseeker.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = JobseekerController.class)
public class JobseekerControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e)
    {
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }

    
}
