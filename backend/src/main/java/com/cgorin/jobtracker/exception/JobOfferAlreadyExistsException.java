package com.cgorin.jobtracker.exception;

public class JobOfferAlreadyExistsException extends RuntimeException {
    public JobOfferAlreadyExistsException(Long id) {
        super("Offer"  + id + " already exists");
    }
}
