package com.cgorin.jobtracker.exception;

public class JobOfferNotFoundException extends RuntimeException {
    public JobOfferNotFoundException(Long id) {
        super("Job offer with id " + id + " not found");
    }
}
