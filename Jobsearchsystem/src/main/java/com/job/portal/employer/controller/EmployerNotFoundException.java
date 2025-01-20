package com.job.portal.employer.controller;

public class EmployerNotFoundException extends RuntimeException {

    public EmployerNotFoundException() {
        super("Customer not found");
    }

    public EmployerNotFoundException(String message) {
        super(message);
    }

    

}