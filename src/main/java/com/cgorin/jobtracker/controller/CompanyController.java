package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.service.CompanyService;
import com.cgorin.jobtracker.model.Company;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

    @PutMapping("/companies/{id}")
    public void updateCompany(@RequestBody Company company, @PathVariable Integer id) {
        companyService.updateCompany(company, id);
    }
}
