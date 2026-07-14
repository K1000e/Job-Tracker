package com.cgorin.jobtracker.exception;

public class CompanyNotFoundException extends Exception {
    public CompanyNotFoundException() {
        super("Company not found");
    }
    public CompanyNotFoundException(String message) {
        super(message);
    }
    public CompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
