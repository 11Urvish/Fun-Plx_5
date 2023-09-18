package com.fundplex.mainrestapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.Company;
import com.fundplex.mainrestapi.dao.CompanyRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service
public class CompanyService {
    @Autowired
    public CompanyRepo companyRepo;

    public void addCompany(Company company) {
        this.companyRepo.save(company);
    }

    public List<Company> getAllCompanies() {
        return this.companyRepo.findAll();
    }

    public void updateCompany(Company company, Long id) {
        this.companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", id));
        company.setId(id);
        this.companyRepo.save(company);

    }

    public void deleteCompany(Long id) {
        this.companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", id));
        this.companyRepo.deleteById(id);
    }

    public Company getCompanyById(Long id) {
        return this.companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "Id", id));
    }

}
