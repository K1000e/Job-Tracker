package com.cgorin.jobtracker.exception;

public class InvalidJobOfferException extends RuntimeException {
    public InvalidJobOfferException(String message) {
        super(message);
    }
}
