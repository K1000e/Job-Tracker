package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.exception.CompanyHasJobOffersException;
import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.repository.CompanyRepository;
import com.cgorin.jobtracker.exception.InvalidCompanyException;
import com.cgorin.jobtracker.exception.CompanyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }
    public Company getCompany(Long id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company addCompany(Company company) {
        if (company.getId() != null) {
            throw new InvalidCompanyException("Cannot specify id when creating a company");
        }
        validateCompany(company);
        return companyRepository.save(company);
    }

    private void validateCompany(Company company) {
        if (company.getName() == null || company.getName().isBlank()) {
            throw new InvalidCompanyException("name");
        }
        if (company.getWebsite() == null || company.getWebsite().isBlank()) {
            throw new InvalidCompanyException("website");
        }
    }

    public void deleteCompany(Long id) {
        if (!getCompany(id).getJobOffers().isEmpty()) {
            throw new CompanyHasJobOffersException(id);
        }
        companyRepository.deleteById(id);
    }
    public Company updateCompany(Company company, Long id) {
        Company company1 = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        company1.setName(company.getName());
        company1.setWebsite(company.getWebsite());
        validateCompany(company1);
        return companyRepository.save(company1);
    }
}
