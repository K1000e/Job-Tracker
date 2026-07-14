package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.model.Company;
import com.cgorin.jobtracker.exception.InvalidCompanyException;
import com.cgorin.jobtracker.exception.CompanyNotFoundException;
import com.cgorin.jobtracker.exception.CompanyAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CompanyService {
    private final List<Company> companies;

    public CompanyService() {
        companies = new ArrayList<>();

        companies.add(new Company(
                0,
                "Capgemini",
                "https://www.capgemini.com"
        ));
        companies.add(new Company(
                1,
                "Amadeus",
                "https://amadeus.com"
        ));
    }
    // ou
    // private List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies(){
        return new ArrayList<>(companies);
    }
    public Company getCompany(int id){
        for (Company company : companies){
            if (company.getId() == id){
                return company;
            }
        }
        throw new CompanyNotFoundException(id);
    }

    public void addCompany(Company company) {
        if (existsById(company)){
            throw new CompanyAlreadyExistsException(company.getId());
        }
        validateCompany(company);
        companies.add(company);
    }

    private void validateCompany(Company company) {
        if (company.getName() == null || company.getName().isBlank()) {
            throw new InvalidCompanyException("Company name is required");
        }
        if (company.getWebsite() == null || company.getWebsite().isBlank()) {
            throw new InvalidCompanyException("Company website is required");
        }
    }

    public void deleteCompany(int id) {
        if (!companies.removeIf(company -> company.getId() == id)){
            throw new CompanyNotFoundException(id);
        }
    }
    public void updateCompany(Company company, int id) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId() == id) {
                validateCompany(company);
                // ignore id sent in the request and use the id from the URL
                Company updated = new Company(id, company.getName(), company.getWebsite());
                companies.set(i, updated);
                return;
            }
        }
        throw new CompanyNotFoundException(id);
    }

    private boolean existsById(Company company){
        return companies.stream().anyMatch(o -> o.getId() == company.getId());
    }
}
