package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.service.JobOfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cgorin.jobtracker.model.JobOffer;

import java.util.List;

@RestController
public class JobOfferController {
    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) { this.jobOfferService = jobOfferService; }

    @GetMapping("/job_offers")
    public List<JobOffer> getAllJobOffers(){ return jobOfferService.getJobOffers();}

    @PostMapping("/job_offers")
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody @Valid JobOffer jobOffer){
        JobOffer offer = jobOfferService.addJobOffer(jobOffer);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping("/job_offers/{id}")
    public JobOffer getJobOfferById(@PathVariable Long id){
        return jobOfferService.getJobOffer(id);
    }

    @DeleteMapping("/job_offers/{id}")
    public ResponseEntity<Void> deleteJobOfferById(@PathVariable Long id){
        jobOfferService.deleteJobOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/job_offers/{id}")
    public ResponseEntity<JobOffer> updateJobOfferById(@PathVariable Long id, @RequestBody @Valid JobOffer jobOffer){
        jobOfferService.updateJobOffer(jobOffer, id);
        return ResponseEntity.ok(jobOffer);
    }

    @GetMapping("/job_offers/status/{status}")
    public List<JobOffer> getJobOffersByStatus(@PathVariable Status status){
        return jobOfferService.getByStatus(status);
    }

    @GetMapping("/job_offers/company/{id}")
    public List<JobOffer> getJobOffersByCompanyId(@PathVariable Long id){
        return jobOfferService.getByCompanyId(id);
    }
}
