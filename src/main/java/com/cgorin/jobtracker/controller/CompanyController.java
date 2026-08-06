package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.service.CompanyService;
import com.cgorin.jobtracker.model.Company;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company) {
        Company savedCompany = companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company company, @PathVariable Long id) {
        Company savedCompany = companyService.updateCompany(company, id);
        return ResponseEntity.ok(savedCompany);
    }
}
