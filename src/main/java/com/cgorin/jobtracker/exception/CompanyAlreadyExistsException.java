package com.cgorin.jobtracker.exception;

public class CompanyAlreadyExistsException extends Exception {
    public CompanyAlreadyExistsException() {
        super("Company already exists");
    }
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
    public CompanyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
