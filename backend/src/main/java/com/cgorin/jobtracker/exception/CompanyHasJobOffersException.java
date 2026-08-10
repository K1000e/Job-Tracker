package com.cgorin.jobtracker.exception;

public class CompanyHasJobOffersException extends RuntimeException {
    public CompanyHasJobOffersException(Long id) {
        super("Company with id " + id + "still has job offers");
    }
}
