package com.cgorin.jobtracker.model;
import jakarta.validation.constraints.NotBlank;

public class Company {
    //Attributes
    private int id;

    @NotBlank(message = "Company name cannot be blank")
    private String name;

    @NotBlank(message = "Company Website cannot be blank")
    private String website;

    public Company() {}
    public Company(int id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }
    //Getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getWebsite() {
        return website;
    }
}
