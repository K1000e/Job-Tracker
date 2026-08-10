package com.cgorin.jobtracker.integration;

import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.service.CompanyService;
import com.cgorin.jobtracker.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CompanyIntegrationTest {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;


    @Test
    void addCompany_shouldPersistCompany(){
        Company company = new Company(
                "Amadeus",
                "https://amadeus.fr"
        );
        companyService.addCompany(company);
        List<Company> result = companyService.getCompanies();
        assertEquals(1,  result.size());
        assertEquals("Amadeus", result.get(0).getName());
    }

    @Test
    void getCompany_shouldReturnPersistedCompany() {

        Company company = new Company(
                "Capgemini",
                "https://capgemini.com"
        );

        Company saved = companyRepository.save(company);

        Company result = companyService.getCompany(saved.getId());

        assertEquals("Capgemini", result.getName());
    }

    @Test
    void deleteCompany_shouldRemoveCompany() {

        Company company = companyRepository.save(
                new Company(
                        "Thales",
                        "https://thalesgroup.com"
                )
        );

        companyService.deleteCompany(company.getId());

        assertEquals(
                0,
                companyRepository.findAll().size()
        );
    }
}
