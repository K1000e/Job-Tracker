package com.cgorin.jobtracker.service;

import com.cgorin.jobtracker.model.Company;
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
                "Sophia Antipolis",
                "https://www.capgemini.com"
        ));
        companies.add(new Company(
                1,
                "Amadeus",
                "Nice",
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
        throw new RuntimeException("Company with id " + id + " not found");
    }

    public void addCompany(Company company){
        if (companyAlreadyExist(company)){
            throw new RuntimeException("Company with id " + company.getId() + " already exists");
        }
        if  (company.getName() == null){
            throw new RuntimeException("Company name is required");
        }
        if (company.getWebsite() == null){
            throw new RuntimeException("Company website is required");
        }
        if (company.getCity() == null){
            throw new RuntimeException("Company city is required");
        }
        companies.add(company);
    }
    public void deleteCompany(int id){
        if (!companies.removeIf(company -> company.getId() == id)){
            throw new RuntimeException("Company with id " + id + " not found");
        }
    }
    public void updateCompany(Company company, int id){
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId() == id) {

                // validate required fields (same checks as addCompany)
                if (company.getName() == null){
                    throw new RuntimeException("Company name is required");
                }
                if (company.getWebsite() == null){
                    throw new RuntimeException("Company website is required");
                }
                if (company.getCity() == null){
                    throw new RuntimeException("Company city is required");
                }

                // ignore id sent in the request and use the id from the URL
                Company updated = new Company(id, company.getName(), company.getCity(), company.getWebsite());
                companies.set(i, updated);
                return;
            }
        }
        throw new RuntimeException("Company id not found");
    }

    private boolean companyAlreadyExist(Company company){
        for (Company existingCompany : companies){
            if (existingCompany.getId() == company.getId()){
                return true;
            }
        }
        return false;
    }
}
