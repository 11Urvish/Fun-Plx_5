package com.fundplex.mainrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundplex.mainrestapi.Model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}
