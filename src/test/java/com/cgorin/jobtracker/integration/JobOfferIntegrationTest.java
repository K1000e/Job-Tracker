package com.cgorin.jobtracker.integration;

import com.cgorin.jobtracker.exception.CompanyHasJobOffersException;
import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.OfferType;
import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.repository.CompanyRepository;
import com.cgorin.jobtracker.repository.JobOfferRepository;
import com.cgorin.jobtracker.service.CompanyService;
import com.cgorin.jobtracker.service.JobOfferService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class JobOfferIntegrationTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EntityManager entityManager;

    @Test
    void addJobOffer_shouldPersistJobOffer() {
        Company company = new Company(
                "Amadeus",
                "https://amadeus.fr"
        );
        companyRepository.save(company);
        JobOffer jobOffer = new JobOffer(
                "Backend Java Developer",
                company,
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
        );
        jobOfferRepository.save(jobOffer);
        List<JobOffer> result = jobOfferRepository.findAll();
        assertEquals(1,  result.size());
        assertEquals("Backend Java Developer", result.get(0).getTitle());
        assertEquals("Amadeus", result.get(0).getCompany().getName());
    }

    @Test
    void getJobOffer_shouldReturnPersistedJobOffer() {
        Company company = new Company(
                "Capgemini",
                "https://Capgemini.fr"
        );
        companyRepository.save(company);

        JobOffer jobOffer = new JobOffer(
                "Frontend Developer",
                company,
                "Nice",
                Status.APPLIED,
                "https://...",
                "Alternance",
                LocalDate.now(),
                null,
                false,
                OfferType.APPRENTICESHIP,
                "Jean",
                "jean@pierre.com"
        );
        jobOfferRepository.save(jobOffer);
        JobOffer result = jobOfferRepository.getJobOfferById(jobOffer.getId());
        assertEquals("Frontend Developer", result.getTitle());
    }
    @Test
    void deleteJobOffer_shouldRemoveJobOffer() {
        Company company = new Company(
                "Thales",
                "https://Thales.fr"
        );
        companyRepository.save(company);

        JobOffer jobOffer = new JobOffer(
                "DevOps Developer",
                company,
                "Bordeaux",
                Status.APPLIED,
                "https://...",
                "CDD",
                LocalDate.now(),
                null,
                false,
                OfferType.CDD,
                "Alain",
                "Alain@thales.com"
        );
        jobOfferRepository.save(jobOffer);
        jobOfferService.deleteJobOffer(jobOffer.getId());
        assertEquals(0, jobOfferRepository.findAll().size());
    }

    @Test
    void deleteCompany_shouldFailWhenCompanyHasOffers() {
        Company company = companyRepository.save(
                new Company(
                        "Thales",
                        "https://Thales.fr"
                )
        );

        JobOffer jobOffer = new JobOffer(
                "DevOps Developer",
                company,
                "Bordeaux",
                Status.APPLIED,
                "https://...",
                "CDD",
                LocalDate.now(),
                null,
                false,
                OfferType.CDD,
                "Alain",
                "Alain@thales.com"
        );

        jobOfferRepository.save(jobOffer);

        entityManager.flush();
        entityManager.clear();

        assertThrows(
                CompanyHasJobOffersException.class,
                () -> companyService.deleteCompany(company.getId())
        );
    }

    @Test
    void getJobOffersByCompany_shouldReturnOffers() {

        Company company = companyRepository.save(
                new Company(
                        "Amadeus",
                        "https://amadeus.com"
                )
        );

        JobOffer offer = new JobOffer(
                "Java Developer",
                company,
                "Sophia Antipolis",
                Status.APPLIED,
                null,
                null,
                null,
                null,
                true,
                OfferType.APPRENTICESHIP,
                null,
                null
        );

        jobOfferRepository.save(offer);

        List<JobOffer> result =
                jobOfferService.getByCompanyId(company.getId());

        assertEquals(1, result.size());
        assertEquals(
                "Java Developer",
                result.get(0).getTitle()
        );
    }
}