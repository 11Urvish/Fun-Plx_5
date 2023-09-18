package com.fundplex.mainrestapi.country;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {

    public Optional<Country> findById(Long id);

}
