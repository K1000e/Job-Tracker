package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.exception.InvalidJobOfferException;
import com.cgorin.jobtracker.exception.JobOfferNotFoundException;
import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.OfferType;
import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.repository.CompanyRepository;
import com.cgorin.jobtracker.repository.JobOfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobOfferServiceTest {

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private JobOfferService jobOfferService;

    private Company company;

    @BeforeEach
    void setup() {
        company = new Company(
                "Amadeus",
                "https://amadeus.com"
        );
    }

    private JobOffer createJobOffer() {
        return new JobOffer(
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
    }


    @Test
    void getJobOffers_shouldReturnJobOfferList() {

        JobOffer offer = createJobOffer();

        when(jobOfferRepository.findAll())
                .thenReturn(List.of(offer));

        List<JobOffer> result = jobOfferService.getJobOffers();

        assertEquals(1, result.size());
        assertTrue(result.contains(offer));

        verify(jobOfferRepository).findAll();
    }


    @Test
    void getJobOffer_shouldReturnJobOffer() {

        JobOffer offer = createJobOffer();

        when(jobOfferRepository.findById(1L))
                .thenReturn(Optional.of(offer));

        JobOffer result = jobOfferService.getJobOffer(1L);

        assertEquals(offer, result);

        verify(jobOfferRepository).findById(1L);
    }


    @Test
    void getJobOffer_shouldReturnNotFound() {

        when(jobOfferRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                JobOfferNotFoundException.class,
                () -> jobOfferService.getJobOffer(1L)
        );

        verify(jobOfferRepository).findById(1L);
    }


    @Test
    void addJobOffer_shouldAddJobOffer() {

        JobOffer offer = createJobOffer();

        when(companyRepository.findById(null))
                .thenReturn(Optional.of(company));

        jobOfferService.addJobOffer(offer);

        verify(jobOfferRepository)
                .save(offer);
    }


    @Test
    void addJobOffer_shouldReturnInvalidJobOffer() {

        JobOffer offer = createJobOffer();

        offer.setTitle("");

        assertThrows(
                InvalidJobOfferException.class,
                () -> jobOfferService.addJobOffer(offer)
        );

        verify(jobOfferRepository, never())
                .save(any());
    }


    @Test
    void updateJobOffer_shouldUpdateJobOffer() {

        JobOffer oldOffer = createJobOffer();

        when(jobOfferRepository.findById(1L))
                .thenReturn(Optional.of(oldOffer));

        when(companyRepository.findById(null))
                .thenReturn(Optional.of(company));


        JobOffer newOffer = createJobOffer();

        newOffer.setTitle("Frontend Developer");
        newOffer.setLocation("Paris");
        newOffer.setStatus(Status.OFFER);
        newOffer.setOfferType(OfferType.CDI);


        jobOfferService.updateJobOffer(newOffer, 1L);


        verify(jobOfferRepository)
                .save(argThat(offer ->
                        offer.getTitle().equals("Frontend Developer")
                                && offer.getLocation().equals("Paris")
                                && offer.getStatus().equals(Status.OFFER)
                                && offer.getOfferType().equals(OfferType.CDI)
                ));
    }


    @Test
    void updateJobOffer_shouldReturnNotFound() {

        when(jobOfferRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                JobOfferNotFoundException.class,
                () -> jobOfferService.updateJobOffer(createJobOffer(),1L)
        );


        verify(jobOfferRepository, never())
                .save(any());
    }


    @Test
    void deleteJobOffer_shouldDeleteJobOffer(){

        when(jobOfferRepository.existsById(1L))
                .thenReturn(true);


        jobOfferService.deleteJobOffer(1L);


        verify(jobOfferRepository)
                .deleteById(1L);
    }


    @Test
    void deleteJobOffer_shouldReturnNotFound(){

        when(jobOfferRepository.existsById(1L))
                .thenReturn(false);


        assertThrows(
                JobOfferNotFoundException.class,
                () -> jobOfferService.deleteJobOffer(1L)
        );


        verify(jobOfferRepository, never())
                .deleteById(1L);
    }


    @Test
    void getByCompanyId_shouldReturnJobOffers(){

        JobOffer offer1 = createJobOffer();
        JobOffer offer2 = createJobOffer();


        when(jobOfferRepository.findByCompanyId(1L))
                .thenReturn(Arrays.asList(offer1, offer2));


        List<JobOffer> result =
                jobOfferService.getByCompanyId(1L);


        assertEquals(2, result.size());

        verify(jobOfferRepository)
                .findByCompanyId(1L);
    }


    @Test
    void getByStatus_shouldReturnJobOffers(){

        when(jobOfferRepository.findByStatus(Status.OFFER))
                .thenReturn(List.of(createJobOffer()));


        List<JobOffer> result =
                jobOfferService.getByStatus(Status.OFFER);


        assertEquals(1, result.size());

        verify(jobOfferRepository)
                .findByStatus(Status.OFFER);
    }
}