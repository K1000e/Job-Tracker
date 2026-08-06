package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.exception.CompanyNotFoundException;
import com.cgorin.jobtracker.exception.InvalidJobOfferException;
import com.cgorin.jobtracker.exception.JobOfferNotFoundException;import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.repository.CompanyRepository;
import com.cgorin.jobtracker.repository.JobOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    private final CompanyRepository companyRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository, CompanyRepository companyRepository) {
        this.jobOfferRepository = jobOfferRepository;
        this.companyRepository = companyRepository;
    }

    public JobOffer getJobOffer(Long id) {
        return jobOfferRepository.findById(id)
                .orElseThrow(() -> new JobOfferNotFoundException(id));
    }

    public List<JobOffer> getJobOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer addJobOffer(JobOffer jobOffer) {
        if (jobOffer.getId() != null) {
            throw new InvalidJobOfferException("Cannot specify id when creating a job offer");
        }
        validateJobOffer(jobOffer);
        return jobOfferRepository.save(jobOffer);
    }
    public void deleteJobOffer(Long id) {
        if (!jobOfferRepository.existsById(id)) {
            throw new JobOfferNotFoundException(id);
        }
        jobOfferRepository.deleteById(id);
    }
    public JobOffer updateJobOffer(JobOffer jobOffer,  Long id) {
        JobOffer offer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new JobOfferNotFoundException(id));
        offer.setTitle(jobOffer.getTitle());
        offer.setCompany(jobOffer.getCompany());
        offer.setLocation(jobOffer.getLocation());
        offer.setStatus(jobOffer.getStatus());
        offer.setJobUrl(jobOffer.getJobUrl());
        offer.setNotes(jobOffer.getNotes());
        offer.setApplicationDate(jobOffer.getApplicationDate());
        offer.setInterviewDate(jobOffer.getInterviewDate());
        offer.setRemote(jobOffer.isRemote());
        offer.setOfferType(jobOffer.getOfferType());
        offer.setContact(jobOffer.getContact());
        offer.setEmail(jobOffer.getEmail());
        validateJobOffer(offer);
        return jobOfferRepository.save(offer);
    }

    public List<JobOffer> getByCompany(Company company) {
        return jobOfferRepository.findByCompany(company);
    }

    public List<JobOffer> getByStatus(Status status)     {
       return jobOfferRepository.findByStatus(status);
    }

    public List<JobOffer> getByCompanyId(Long id) {
        return jobOfferRepository.findByCompanyId(id);
    }

    private void validateJobOffer(JobOffer jobOffer) {
        if (jobOffer.getTitle() == null || jobOffer.getTitle().isBlank())
            throw new InvalidJobOfferException("Title is required");

        if (jobOffer.getLocation() == null || jobOffer.getLocation().isBlank())
            throw new InvalidJobOfferException("Location is required");

        if (jobOffer.getStatus() == null)
            throw new InvalidJobOfferException("Status is required");

        if (jobOffer.getOfferType() == null)
            throw new InvalidJobOfferException("Offer type is required");

        Company storedCompany = companyRepository.findById(jobOffer.getCompany().getId())
                .orElseThrow(() -> new CompanyNotFoundException(jobOffer.getCompany().getId()));

        if (!storedCompany.equals(jobOffer.getCompany())) {
            throw new InvalidJobOfferException("Job offer company does not match stored company");
        }
    }
}
