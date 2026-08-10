package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.model.OfferType;
import com.cgorin.jobtracker.model.Status;
import com.cgorin.jobtracker.exception.JobOfferNotFoundException;
import com.cgorin.jobtracker.service.JobOfferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobOfferController.class)
class JobOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobOfferService jobOfferService;


    private Company createCompany() {
        return new Company(
                "Amadeus",
                "https://amadeus.com"
        );
    }


    private JobOffer createOffer() {
        return new JobOffer(
                "Backend Java Developer",
                createCompany(),
                "Sophia Antipolis",
                Status.APPLIED,
                "https://offer.com",
                "Alternance Spring Boot",
                LocalDate.now(),
                null,
                false,
                OfferType.APPRENTICESHIP,
                "Jean Dupont",
                "jean@amadeus.com"
        );
    }


    @Test
    void getAllJobOffers_shouldReturnOffers() throws Exception {

        when(jobOfferService.getJobOffers())
                .thenReturn(List.of(createOffer()));

        mockMvc.perform(get("/job-offers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Backend Java Developer"));

        verify(jobOfferService).getJobOffers();
    }


    @Test
    void getJobOfferById_shouldReturnOffer() throws Exception {

        when(jobOfferService.getJobOffer(1L))
                .thenReturn(createOffer());

        mockMvc.perform(get("/job-offers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title")
                        .value("Backend Java Developer"));

        verify(jobOfferService).getJobOffer(1L);
    }


    @Test
    void getJobOfferById_shouldReturnNotFound() throws Exception {

        when(jobOfferService.getJobOffer(1L))
                .thenThrow(new JobOfferNotFoundException(1L));

        mockMvc.perform(get("/job-offers/1"))
                .andExpect(status().isNotFound());

        verify(jobOfferService).getJobOffer(1L);
    }


    @Test
    void createJobOffer_shouldReturnCreated() throws Exception {

        JobOffer offer = createOffer();

        when(jobOfferService.addJobOffer(any(JobOffer.class)))
                .thenReturn(offer);


        mockMvc.perform(post("/job-offers")
                        .contentType(APPLICATION_JSON)
                        .content("""
                        {
                            "title": "Backend Java Developer",
                            "company": {
                                "id": 1,
                                "name": "Amadeus",
                                "website": "https://amadeus.com"
                            },
                            "location": "Sophia Antipolis",
                            "status": "APPLIED",
                            "jobUrl": "https://offer.com",
                            "notes": "Alternance Spring Boot",
                            "applicationDate": "2026-08-07",
                            "remote": false,
                            "offerType": "APPRENTICESHIP",
                            "contact": "Jean Dupont",
                            "email": "jean@amadeus.com"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title")
                        .value("Backend Java Developer"));

        verify(jobOfferService)
                .addJobOffer(any(JobOffer.class));
    }


    @Test
    void createJobOffer_shouldReturnBadRequestWhenInvalid() throws Exception {

        mockMvc.perform(post("/job-offers")
                        .contentType(APPLICATION_JSON)
                        .content("""
                        {
                            "title": "",
                            "location": "",
                            "status": null,
                            "offerType": null
                        }
                        """))
                .andExpect(status().isBadRequest());

        verify(jobOfferService, never())
                .addJobOffer(any());
    }


    @Test
    void deleteJobOffer_shouldReturnNoContent() throws Exception {

        doNothing()
                .when(jobOfferService)
                .deleteJobOffer(1L);

        mockMvc.perform(delete("/job-offers/1"))
                .andExpect(status().isNoContent());

        verify(jobOfferService)
                .deleteJobOffer(1L);
    }


    @Test
    void updateJobOffer_shouldReturnUpdatedOffer() throws Exception {

        JobOffer offer = createOffer();

        when(jobOfferService.updateJobOffer(any(JobOffer.class), eq(1L)))
                .thenReturn(offer);


        mockMvc.perform(put("/job-offers/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                        {
                            "title": "Backend Java Developer",
                            "company": {
                                "id": 1,
                                "name": "Amadeus",
                                "website": "https://amadeus.com"
                            },
                            "location": "Nice",
                            "status": "OFFER",
                            "jobUrl": "https://offer.com",
                            "notes": "Updated note",
                            "applicationDate": "2026-08-07",
                            "remote": false,
                            "offerType": "CDI",
                            "contact": "Jean Dupont",
                            "email": "jean@amadeus.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title")
                        .value("Backend Java Developer"));

        verify(jobOfferService)
                .updateJobOffer(any(JobOffer.class), eq(1L));
    }


    @Test
    void getJobOffersByStatus_shouldReturnOffers() throws Exception {

        when(jobOfferService.getByStatus(Status.APPLIED))
                .thenReturn(List.of(createOffer()));

        mockMvc.perform(get("/job-offers/status/APPLIED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Backend Java Developer"));

        verify(jobOfferService)
                .getByStatus(Status.APPLIED);
    }


    @Test
    void getJobOffersByCompany_shouldReturnOffers() throws Exception {

        when(jobOfferService.getByCompanyId(1L))
                .thenReturn(List.of(createOffer()));

        mockMvc.perform(get("/job-offers/company/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Backend Java Developer"));

        verify(jobOfferService)
                .getByCompanyId(1L);
    }
}