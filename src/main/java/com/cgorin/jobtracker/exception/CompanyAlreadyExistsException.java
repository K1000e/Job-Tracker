package com.cgorin.jobtracker.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException(int id) {
        super("Company"  + id + " already exists");
    }
}
