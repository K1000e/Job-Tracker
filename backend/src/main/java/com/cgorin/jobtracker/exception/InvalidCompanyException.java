package com.cgorin.jobtracker.exception;

public class InvalidCompanyException extends RuntimeException {
    public InvalidCompanyException(String arg) {
        super("Company " + arg + " is required");
    }
}
