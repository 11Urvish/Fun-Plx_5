package com.fundplex.mainrestapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fundplex.mainrestapi.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    public Optional<Customer> findById(Long id);

    @Query("SELECT c FROM Customer c WHERE lower(c.displayName) LIKE lower(concat('%', :keyword, '%'))")
    List<Customer> findByValue(@Param("keyword") String keyword);
}
