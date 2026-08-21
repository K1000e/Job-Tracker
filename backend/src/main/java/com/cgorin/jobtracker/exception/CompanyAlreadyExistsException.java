package com.cgorin.jobtracker.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException(String name) {
        super("Company '" + name + "' already exists");
    }
}
