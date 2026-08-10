package com.cgorin.jobtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @ManyToOne
    @JoinColumn(name = "company_id",  nullable = false)
    @NotNull
    private Company company;

    @NotBlank
    private String location;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @URL
    private String jobUrl;
    private String notes;
    private LocalDate applicationDate;
    private LocalDateTime interviewDate;
    private boolean remote;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OfferType offerType;
    private String contact;
    @Email
    private String email;


    public JobOffer() {}
    public JobOffer(String title, Company company, String location, Status status, String jobUrl, String notes,  LocalDate applicationDate, LocalDateTime interviewDate, boolean remote, OfferType offerType, String contact, String email) {
        this.title = title;
        this.company = company;
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
    public Long getId() {return id;}
    public String getTitle() {return title;}
    public Company getCompany() {return company;}
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
    public void setTitle(String title) {this.title = title;}
    public void setCompany(Company company) {this.company = company;}
    public void setLocation(String location) {this.location = location;}
    public void setStatus(Status status) {this.status = status;}
    public void setJobUrl(String jobUrl) {this.jobUrl = jobUrl;}
    public void setNotes(String notes) {this.notes = notes;}
    public void setApplicationDate(LocalDate applicationDate) {this.applicationDate = applicationDate;}
    public void setInterviewDate(LocalDateTime interviewDate) {this.interviewDate = interviewDate;}
    public void setRemote(boolean remote) {this.remote = remote;}
    public void setOfferType(OfferType offerType) {this.offerType = offerType;}
    public void setContact(String contact) {this.contact = contact;}
    public void setEmail(String email) {this.email = email;}
}
