package com.cgorin.jobtracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Company Website cannot be blank")
    @Size(max = 255, message = "Company website cannot exceed 255 characters")
    @URL(message = "Company website must be a valid URL")
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
