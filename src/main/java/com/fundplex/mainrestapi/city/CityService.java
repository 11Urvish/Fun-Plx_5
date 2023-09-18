package com.fundplex.mainrestapi.city;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service
public class CityService {
    @Autowired
    public CityRepo cityRepo;

    public void createCity(City city) {
        this.cityRepo.save(city);
    }

    public void updateCity(City city, Long id) {
        this.cityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        city.setId(id);
        this.cityRepo.save(city);
    }

    public void deleteCity(Long id) {
        this.cityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.cityRepo.deleteById(id);
    }

    public List<City> getAllCitys() {
        return this.cityRepo.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return this.cityRepo.findById(id);
    }

}
