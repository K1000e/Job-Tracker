package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.exception.CompanyAlreadyExistsException;
import com.cgorin.jobtracker.exception.CompanyHasJobOffersException;
import com.cgorin.jobtracker.exception.CompanyNotFoundException;
import com.cgorin.jobtracker.exception.InvalidCompanyException;
import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.model.JobOffer;
import com.cgorin.jobtracker.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;


    @BeforeEach
    void init() {
        companyService = new CompanyService(companyRepository);
    }


    @Test
    void getCompanies_shouldReturnCompanyList() {

        Company amadeus =
                new Company("Amadeus", "https://amadeus.com");

        Company capgemini =
                new Company("Capgemini", "https://capgemini.com");


        when(companyRepository.findAll())
                .thenReturn(Arrays.asList(amadeus, capgemini));


        List<Company> result =
                companyService.getCompanies();


        assertEquals(2, result.size());

        verify(companyRepository)
                .findAll();
    }


    @Test
    void getCompanyById_shouldReturnCompany() {

        Company company =
                new Company("Amadeus", "https://amadeus.com");


        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(company));


        assertEquals(
                company,
                companyService.getCompany(1L)
        );
    }


    @Test
    void getCompanyById_shouldReturnNotFound() {

        when(companyRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                CompanyNotFoundException.class,
                () -> companyService.getCompany(1L)
        );
    }


    @Test
    void addCompany_shouldAddCompany() {

        Company company =
                new Company("Amadeus", "https://amadeus.com");


        companyService.addCompany(company);


        verify(companyRepository)
                .save(company);
    }


    @Test
    void addCompany_shouldReturnInvalidCompany() {

        Company company =
                new Company("", "https://amadeus.com");


        assertThrows(
                InvalidCompanyException.class,
                () -> companyService.addCompany(company)
        );


        verify(companyRepository, never())
                .save(any());
    }

	@Test
	void addCompany_shouldReturnConflictWhenNameAlreadyExists() {
		when(companyRepository.existsByNameIgnoreCase("Amadeus")).thenReturn(true);
		Company company = new Company("Amadeus", "https://amadeus.com");

		assertThrows(CompanyAlreadyExistsException.class,
				() -> companyService.addCompany(company));

		verify(companyRepository, never()).save(any());
	}

    @Test
    void deleteCompany_shouldDeleteCompany() {

        Company company =
                new Company("Amadeus", "https://amadeus.com");


        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(company));


        companyService.deleteCompany(1L);


        verify(companyRepository)
                .deleteById(1L);
    }


    @Test
    void deleteCompany_shouldReturnCompanyStillHasJobOffer() {

        Company company =
                new Company("Amadeus", "https://amadeus.com");


        company.getJobOffers()
                .add(mock(JobOffer.class));


        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(company));


        assertThrows(
                CompanyHasJobOffersException.class,
                () -> companyService.deleteCompany(1L)
        );


        verify(companyRepository, never())
                .deleteById(anyLong());
    }


    @Test
    void deleteCompany_shouldReturnNotFound() {

        when(companyRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                CompanyNotFoundException.class,
                () -> companyService.deleteCompany(1L)
        );


        verify(companyRepository, never())
                .deleteById(anyLong());
    }


    @Test
    void updateCompany_shouldUpdateCompany() {

        Company oldCompany =
                new Company("Amadeus", "https://amadeus.com");


        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(oldCompany));


        Company updated =
                new Company("Amadeus France", "https://amadeus.com");


        companyService.updateCompany(updated,1L);


        verify(companyRepository)
                .save(argThat(company ->
                        company.getName().equals("Amadeus France")
                                && company.getWebsite().equals("https://amadeus.com")
                ));
    }


    @Test
    void updateCompany_shouldReturnNotFound() {

        when(companyRepository.findById(1L))
                .thenReturn(Optional.empty());


        assertThrows(
                CompanyNotFoundException.class,
                () -> companyService.updateCompany(
                        new Company("Amadeus","https://amadeus.com"),
                        1L
                )
        );


        verify(companyRepository, never())
                .save(any());
    }
}