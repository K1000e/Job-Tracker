package com.cgorin.jobtracker.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException(Long id) {
        super("Company"  + id + " already exists");
    }
}
