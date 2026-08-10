package com.cgorin.jobtracker.repository;

import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByStatus(Status status);

    List<JobOffer> findByCompanyId(Long id);

    JobOffer getJobOfferById(Long id);
}
