package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.service.JobOfferService;
import org.springframework.web.bind.annotation.*;
import com.cgorin.jobtracker.model.JobOffer;

import java.util.List;

@RestController
public class JobOfferController {
    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) { this.jobOfferService = jobOfferService; }

    @GetMapping("/offers")
    public List<JobOffer> getAllJobOffers(){ return jobOfferService.getOffers();}

    @PostMapping("/offers")
    public void createJobOffer(@RequestBody JobOffer jobOffer){
        jobOfferService.addJobOffer(jobOffer);
    }

    @GetMapping("/offers/{id}")
    public JobOffer getJobOfferById(@PathVariable int id){
        return jobOfferService.getJobOffer(id);
    }

    @DeleteMapping("/offers/{id}")
    public void deleteJobOfferById(@PathVariable int id){
        jobOfferService.deleteJobOffer(id);
    }

    @PutMapping("/offers/{id}")
    public void updateJobOfferById(@PathVariable int id, @RequestBody JobOffer jobOffer){
        jobOfferService.updateJobOffer(jobOffer, id);
    }

    @GetMapping("/offers/status/{status}")
    public List<JobOffer> getJobOffersByStatus(@PathVariable Status status){
        return jobOfferService.getByStatus(status);
    }

    @GetMapping("/offers/company/{id}")
    public List<JobOffer> getJobOffersByCompanyId(@PathVariable int id){
        return jobOfferService.getByCompanyId(id);
    }
}
