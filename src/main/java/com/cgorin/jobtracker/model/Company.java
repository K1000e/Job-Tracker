package com.cgorin.jobtracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name cannot be blank")
    private String name;

    @NotBlank(message = "Company Website cannot be blank")
    private String website;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<JobOffer> jobOffers =  new ArrayList<>();
    public Company() {}
    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getWebsite() {
        return website;
    }
    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
}
