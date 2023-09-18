package com.fundplex.mainrestapi.services;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.Customer;
import com.fundplex.mainrestapi.dao.CustomerRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepo customerRepo;

    public void createCustomer(Customer customer) {
        this.customerRepo.save(customer);
    }

    public void updateCustomer(Customer customer, Long id) {
        this.customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        customer.setId(id);
        this.customerRepo.save(customer);
    }

    public void deleteCustomer(Long id) {
        this.customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.customerRepo.deleteById(id);
    }

    public CustomerResponse getAllCustomers(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = null;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Customer> pagecustomer = this.customerRepo.findAll(pageable);
        List<Customer> allcustomers = pagecustomer.getContent();

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setContent(allcustomers);
        customerResponse.setTotalCount(customerRepo.count());
        customerResponse.setPageNumber(pagecustomer.getNumber());
        customerResponse.setPageSize(pagecustomer.getSize());
        customerResponse.setTotalElements(pagecustomer.getNumberOfElements());
        customerResponse.setTotalPages(pagecustomer.getTotalPages());
        customerResponse.setLastPage(pagecustomer.isLast());

        return customerResponse;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return this.customerRepo.findById(id);
    }

    public List<Customer> getCustomerByValue(String filter) {
        return customerRepo.findByValue(filter);
    }
}
