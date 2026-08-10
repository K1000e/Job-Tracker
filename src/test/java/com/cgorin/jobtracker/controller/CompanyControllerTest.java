package com.cgorin.jobtracker.controller;

import com.cgorin.jobtracker.exception.CompanyNotFoundException;
import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompanyService companyService;

    @Test
    void getCompanies_shouldReturnCompanies() throws Exception {
        Company amadeus = new Company(
                "Amadeus",
                "https://amadeus.com"
        );

        Company capgemini = new Company(
                "Capgemini",
                "https://capgemini.com"
        );

        when(companyService.getCompanies())
                .thenReturn(List.of(amadeus, capgemini));

        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Amadeus"))
                .andExpect(jsonPath("$[1].name").value("Capgemini"));

        verify(companyService).getCompanies();
    }

    @Test
    void getCompany_shouldReturnCompany() throws Exception {
        Company company = new Company(
                "Amadeus",
                "https://amadeus.com"
        );

        when(companyService.getCompany(1L))
                .thenReturn(company);

        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Amadeus"))
                .andExpect(jsonPath("$.website").value("https://amadeus.com"));

        verify(companyService).getCompany(1L);
    }

    @Test
    void getCompany_shouldReturnNotFound() throws Exception {
        when(companyService.getCompany(1L))
                .thenThrow(new CompanyNotFoundException(1L));

        mockMvc.perform(get("/companies/1"))
                .andExpect(status().isNotFound());

        verify(companyService).getCompany(1L);
    }

    @Test
    void addCompany_shouldCreateCompany() throws Exception {
        Company company = new Company(
                "Amadeus",
                "https://amadeus.com"
        );

        when(companyService.addCompany(any(Company.class)))
                .thenReturn(company);

        mockMvc.perform(post("/companies")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Amadeus",
                                    "website": "https://amadeus.com"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Amadeus"))
                .andExpect(jsonPath("$.website").value("https://amadeus.com"));

        verify(companyService).addCompany(any(Company.class));
    }

    @Test
    void addCompany_shouldReturnBadRequestWhenInvalid() throws Exception {
        mockMvc.perform(post("/companies")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "",
                                    "website": "https://amadeus.com"
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(companyService, never()).addCompany(any());
    }

    @Test
    void deleteCompany_shouldReturnNoContent() throws Exception {
        doNothing()
                .when(companyService)
                .deleteCompany(1L);

        mockMvc.perform(delete("/companies/1"))
                .andExpect(status().isNoContent());

        verify(companyService).deleteCompany(1L);
    }

    @Test
    void updateCompany_shouldReturnUpdatedCompany() throws Exception {
        Company company = new Company(
                "Amadeus France",
                "https://amadeus.com"
        );

        when(companyService.updateCompany(any(Company.class), eq(1L)))
                .thenReturn(company);

        mockMvc.perform(put("/companies/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Amadeus France",
                                    "website": "https://amadeus.com"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Amadeus France"))
                .andExpect(jsonPath("$.website").value("https://amadeus.com"));

        verify(companyService).updateCompany(any(Company.class), eq(1L));
    }

    @Test
    void updateCompany_shouldReturnBadRequestWhenInvalid() throws Exception {
        mockMvc.perform(put("/companies/1")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "",
                                    "website": "https://amadeus.com"
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(companyService, never())
                .updateCompany(any(), eq(1L));
    }
}