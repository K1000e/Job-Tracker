package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.service.JobOfferService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cgorin.jobtracker.model.JobOffer;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@Tag(
        name = "Job Offers",
        description = "Endpoints for managing job offers"
)
public class JobOfferController {
    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) { this.jobOfferService = jobOfferService; }

    @GetMapping("/job-offers")
    @Operation(
            summary = "Get all job offers",
            description = "Retrieve every job offer stored in the database."
    )
    public List<JobOffer> getAllJobOffers(){ return jobOfferService.getJobOffers();}

    @PostMapping("/job-offers")
    @Operation(
            summary = "Create a new job offer",
            description = "Add a job offer linked to an existing company."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Job offer created"),
            @ApiResponse(responseCode = "400", description = "Invalid job offer data"),
            @ApiResponse(responseCode = "404", description = "Company not found")
    })
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody @Valid JobOffer jobOffer){
        JobOffer offer = jobOfferService.addJobOffer(jobOffer);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping("/job-offers/{id}")
    @Operation(
            summary = "Get job offer by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Job offer found"),
            @ApiResponse(responseCode = "404", description = "Job offer not found")
    })
    public JobOffer getJobOfferById(@PathVariable Long id){
        return jobOfferService.getJobOffer(id);
    }

    @DeleteMapping("/job-offers/{id}")
    @Operation(
            summary = "Delete a job offer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Job offer deleted"),
            @ApiResponse(responseCode = "404", description = "Job offer not found")
    })
    public ResponseEntity<Void> deleteJobOfferById(@PathVariable Long id){
        jobOfferService.deleteJobOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/job-offers/{id}")
    @Operation(
            summary = "Update a job offer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Job offer updated"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Job offer or company not found")
    })
    public ResponseEntity<JobOffer> updateJobOfferById(
            @PathVariable Long id,
            @RequestBody @Valid JobOffer jobOffer
    ){
        JobOffer updatedOffer = jobOfferService.updateJobOffer(jobOffer, id);
        return ResponseEntity.ok(updatedOffer);
    }

    @GetMapping("/job-offers/status/{status}")
    @Operation(
            summary = "Find job offers by status"
    )
    public List<JobOffer> getJobOffersByStatus(@PathVariable Status status){
        return jobOfferService.getByStatus(status);
    }

    @GetMapping("/job-offers/company/{companyId}")
    @Operation(
            summary = "Find job offers by company"
    )
    public List<JobOffer> getJobOffersByCompanyId(@PathVariable Long companyId){
        return jobOfferService.getByCompanyId(companyId);
    }
}
