package com.fundplex.mainrestapi.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/country")

public class CountryController {

    @Autowired
    public CountryService countryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCountry(@RequestBody Country country) {
        this.countryService.createCountry(country);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Country Added Successfully!!", country, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCountry(@RequestBody Country country) {
        this.countryService.updateCountry(country, country.id);
        return new ResponseEntity<>(new ApiResponse("Country Updated Successfully!!!", country, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCountry(@PathVariable("id") Long id) {
        this.countryService.deleteCountry(id);
        return new ResponseEntity<>(new ApiResponse("Country Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllCountrys() {
        List<Country> countries = this.countryService.getAllCountrys();
        return new ResponseEntity<>(new ApiResponse("List of All Countrys!!!", countries, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getCountryById(@PathVariable("id") Long id) {
        Optional<Country> country = this.countryService.getCountryById(id);
        return new ResponseEntity<>(new ApiResponse("Country Found with this Id", country, true), HttpStatus.OK);
    }

}
