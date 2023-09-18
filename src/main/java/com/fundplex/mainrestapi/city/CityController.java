package com.fundplex.mainrestapi.city;

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
@RequestMapping("/city")

public class CityController {

    @Autowired
    public CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCity(@RequestBody City city) {
        this.cityService.createCity(city);
        return new ResponseEntity<ApiResponse>(new ApiResponse("City Added Successfully!!", city, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCity(@RequestBody City city) {
        this.cityService.updateCity(city, city.id);
        return new ResponseEntity<>(new ApiResponse("City Updated Successfully!!!", city, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable("id") Long id) {
        this.cityService.deleteCity(id);
        return new ResponseEntity<>(new ApiResponse("City Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllCitys() {
        List<City> cities = this.cityService.getAllCitys();
        return new ResponseEntity<>(new ApiResponse("List of All Citys!!!", cities, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getCityById(@PathVariable("id") Long id) {
        Optional<City> city = this.cityService.getCityById(id);
        return new ResponseEntity<>(new ApiResponse("City Found with this Id", city, true), HttpStatus.OK);
    }

}
