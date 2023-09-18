package com.fundplex.mainrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fundplex.mainrestapi.Model.Customer;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.response.CustomerResponse;
import com.fundplex.mainrestapi.services.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Customer customer) {
        customer.setCompany(customer.getCompany());
        customer.setDisplayName(customer.getFirstName() + " " + customer.getLastName());
        this.customerService.createCustomer(customer);
        return new ResponseEntity<>(new ApiResponse("Customer Created Successfully!!!", customer, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCustomer(@RequestBody Customer customer) {
        this.customerService.updateCustomer(customer, customer.id);
        return new ResponseEntity<>(new ApiResponse("Customer Updated Successfully!!!", customer, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>(new ApiResponse("Customer Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllCustomers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {

        CustomerResponse customerResponse = this.customerService.getAllCustomers(pageNumber, pageSize, sortBy,
                sortDirection);
        return new ResponseEntity<>(new ApiResponse("List of Customers", customerResponse, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable("id") Long id) {
        Optional<Customer> customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(new ApiResponse("Customer Found with this Id", customer, true), HttpStatus.OK);
    }

    @PostMapping("/findByValue")
    public ResponseEntity<ApiResponse> getCustomerByValue(@RequestParam("filter") String filter) {
        List<Customer> customers = customerService.getCustomerByValue(filter);
        return new ResponseEntity<>(new ApiResponse("List of All Customers!!!", customers, true), HttpStatus.OK);
    }
}
