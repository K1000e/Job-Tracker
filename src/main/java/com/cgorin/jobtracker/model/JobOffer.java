package com.cgorin.jobtracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobOffer {
    private int id;
    private String title;
    private int companyId;
    private String location;
    private Status status;
    private String jobUrl;
    private String notes;
    private LocalDate applicationDate;
    private LocalDateTime interviewDate;
    private boolean remote;
    private OfferType offerType;
    private String contact;
    private String email;


    public JobOffer() {}
    public JobOffer(int id, String title, int companyId, String location, Status status, String jobUrl, String notes,  LocalDate applicationDate, LocalDateTime interviewDate, boolean remote, OfferType offerType, String contact, String email) {
        this.id = id;
        this.title = title;
        this.companyId = companyId;
        this.location = location;
        this.status = status;
        this.jobUrl = jobUrl;
        this.notes = notes;
        this.applicationDate = applicationDate;
        this.interviewDate = interviewDate;
        this.remote = remote;
        this.offerType = offerType;
        this.contact = contact;
        this.email = email;
    }
    public int getId() {return id;}
    public String getTitle() {return title;}
    public int getCompanyId() {return companyId;}
    public String getLocation() {return location;}
    public Status getStatus() { return status; }
    public String getJobUrl() { return jobUrl; }
    public String getNotes() { return notes; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public LocalDateTime getInterviewDate() { return interviewDate; }
    public boolean isRemote() { return remote; }
    public OfferType getOfferType() { return offerType; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
}
