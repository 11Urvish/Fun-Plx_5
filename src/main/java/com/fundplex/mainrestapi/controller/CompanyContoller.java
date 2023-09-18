package com.fundplex.mainrestapi.controller;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.Model.Company;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.services.CompanyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/company")

public class CompanyContoller {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/findCompanyDetails")
    public ResponseEntity<ApiResponse> getCompanyDetails() {
        Company company = this.companyService.getCompanyById((long) 20);

        return new ResponseEntity<>(new ApiResponse("Showing Company Details", company, true), HttpStatus.ACCEPTED);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getCompanyById(@PathVariable("id") Long id) {
        Company company = this.companyService.getCompanyById(id);
        return new ResponseEntity<>(new ApiResponse("Company Added Successfully!!!",
                company, true),
                HttpStatus.CREATED);
    }

    // @PostMapping("/create")
    // public ResponseEntity<ApiResponse> addCompany(@RequestBody Company company) {
    // // Long id = (long) 1;
    // company.setAddress("510/Trade Square, Khokhra Circle");
    // company.setCity("Ahmedabad");
    // company.setCompanyCode("151");
    // company.setCompanyName("Megnx Software");
    // company.setCompanyType("Company");
    // company.setCountry("India");
    // company.setEmail("megnx.info@gmail.com");
    // company.setId((long) 1);
    // company.setPhone("9898989898");
    // company.setState("Gujarat");
    // company.setZipcode("380008");
    // company.setWebsite("www.megnx.com");
    // company.setStatus((long) 1);

    // this.companyService.addCompany(company);
    // return new ResponseEntity(new ApiResponse("Company Added Successfully!!!",
    // company, true), HttpStatus.CREATED);
    // }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCompany(@RequestBody Company company) {
        this.companyService.updateCompany(company, company.id);
        return new ResponseEntity(new ApiResponse("Company Details Updated Successfully!!", company, true),
                HttpStatus.OK);
    }

    // @PostMapping("/delete/{id}")
    // public ResponseEntity<ApiResponse> deleteCompany(@PathVariable("id") Long id)
    // {
    // this.companyService.deleteCompany(id);
    // return new ResponseEntity(new ApiResponse("Company Details Deleted
    // Successfully!!", null, true), HttpStatus.OK);
    // }

}
