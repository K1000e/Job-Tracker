package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.service.CompanyService;
import com.cgorin.jobtracker.model.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(
        name = "Companies",
        description = "Endpoints for managing companies"
)
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    @Operation(
            summary = "Get all companies"
    )
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @PostMapping("/companies")
    @Operation(
            summary = "Create a company",
            description = "Create a new company entry."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Company created"),
            @ApiResponse(responseCode = "400", description = "Invalid company data"),
            @ApiResponse(responseCode = "409", description = "Company already exists")
    })    public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company) {
        Company savedCompany = companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @GetMapping("/companies/{id}")
    @Operation(
            summary = "Get company by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company found"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    @DeleteMapping("/companies/{id}")
    @Operation(
            summary = "Delete company"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Company deleted"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "409", description = "Company has job offers")
    })
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/companies/{id}")
    @Operation(
            summary = "Update company"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company updated"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid Company company, @PathVariable Long id) {
        Company savedCompany = companyService.updateCompany(company, id);
        return ResponseEntity.ok(savedCompany);
    }
}
