package com.cgorin.jobtracker.exception;

public class JobOffersAlreadyExistsException extends RuntimeException {
    public JobOffersAlreadyExistsException(int id) {
        super("Offer"  + id + " already exists");
    }
}
