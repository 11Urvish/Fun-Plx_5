package com.fundplex.mainrestapi.city;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {

    public Optional<City> findById(Long id);

}