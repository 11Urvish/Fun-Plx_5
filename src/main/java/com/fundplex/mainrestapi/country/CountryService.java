package com.fundplex.mainrestapi.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service

public class CountryService {
    @Autowired
    public CountryRepo countryRepo;

    public void createCountry(Country country) {
        this.countryRepo.save(country);
    }

    public void updateCountry(Country country, Long id) {
        this.countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country", "Id", id));
        country.setId(id);
        this.countryRepo.save(country);
    }

    public void deleteCountry(Long id) {
        this.countryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country", "Id", id));
        this.countryRepo.deleteById(id);
    }

    public List<Country> getAllCountrys() {
        return this.countryRepo.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return this.countryRepo.findById(id);
    }

}
