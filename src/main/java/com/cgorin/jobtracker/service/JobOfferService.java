package com.cgorin.jobtracker.service;
import com.cgorin.jobtracker.exception.InvalidJobOfferException;
import com.cgorin.jobtracker.exception.JobOfferNotFoundException;
import com.cgorin.jobtracker.exception.JobOffersAlreadyExistsException;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.OfferType;
import com.cgorin.jobtracker.model.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobOfferService {
    private final List<JobOffer> offers;

    public JobOfferService() {
        offers = new ArrayList<>();

        offers.add(new JobOffer(
                0,
                "Backend Java Developer",
                1,
                "Sophia Antipolis",
                Status.APPLIED,
                "https://...",
                "Alternance Spring Boot",
                LocalDate.now(),
                null,
                false,
                OfferType.APPRENTICESHIP,
                "Jean Dupont",
                "jean@company.com"
        ));
    }

    public JobOffer getJobOffer(int id) {
        for (JobOffer jobOffer : offers){
            if  (jobOffer.getId() == id){
                return jobOffer;
            }
        }
        throw new JobOfferNotFoundException(id);
        // a changer
    }

    public List<JobOffer> getOffers() { return new ArrayList<>(offers); }

    public void addJobOffer(JobOffer jobOffer) {
        if (existsById(jobOffer))
            throw new JobOffersAlreadyExistsException(jobOffer.getId());
        validateJobOffer(jobOffer);
        offers.add(jobOffer);
    }
    public void deleteJobOffer(int id) {
        if (!offers.removeIf(offer -> offer.getId() == id)) {
            throw new JobOfferNotFoundException(id);
        }
    }
    public void updateJobOffer(JobOffer jobOffer,  int id) {
        validateJobOffer(jobOffer);
        for (int i = 0; i < offers.size(); i++) {
            if (offers.get(i).getId() == id) {
                offers.set(i, jobOffer);
                return;
            }
        }
    }

    public List<JobOffer> getByCompanyId(int companyId) {
        return offers.stream().filter(o -> o.getCompanyId() == companyId).toList();
    }

    public List<JobOffer> getByStatus(Status status) {
        return offers.stream().filter(o -> status == o.getStatus()).toList();
    }

    private boolean existsById(JobOffer jobOffer) {
        return offers.stream().anyMatch(o -> o.getId() == jobOffer.getId());
    }

    private void validateJobOffer(JobOffer jobOffer) {

        if (jobOffer.getTitle() == null || jobOffer.getTitle().isBlank()) {
            throw new InvalidJobOfferException("Title is required");
        }

        if (jobOffer.getCompanyId() < 0) {
            throw new InvalidJobOfferException("Invalid company");
        }

        if (jobOffer.getLocation() == null || jobOffer.getLocation().isBlank()) {
            throw new InvalidJobOfferException("Location is required");
        }

        if (jobOffer.getStatus() == null) {
            throw new InvalidJobOfferException("Status is required");
        }

        if (jobOffer.getOfferType() == null) {
            throw new InvalidJobOfferException("Offer type is required");
        }
    }
}
