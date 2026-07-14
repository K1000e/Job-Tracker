package com.cgorin.jobtracker.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(int id) {
        super("Company with id " + id + " not found");
    }
}
