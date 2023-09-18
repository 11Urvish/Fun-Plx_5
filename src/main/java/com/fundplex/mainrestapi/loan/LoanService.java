package com.fundplex.mainrestapi.loan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.Customer;
import com.fundplex.mainrestapi.dao.CustomerRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.response.LoanResponse;

@Service
public class LoanService {
    @Autowired
    public LoanRepo loanRepo;

    @Autowired
    public CustomerRepo customerRepo;

    public void createLoan(Loan loan) {
        this.loanRepo.save(loan);
    }

    public void updateLoan(Loan loan, Long id) {
        this.loanRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        loan.setId(id);
        this.loanRepo.save(loan);
    }

    public void deleteLoan(Long id) {
        this.loanRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.loanRepo.deleteById(id);
    }

    public List<Loan> getAllLoans() {
        return this.loanRepo.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return this.loanRepo.findById(id);
    }

    public List<Loan> getByCustomerId(Long id) {
        Customer customer = this.customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
        // Long customerId = customer.getId();
        System.out.println("Role found with this id");
        List<Loan> loans = this.loanRepo.findByCustomerId(customer);
        System.out.println("permissions find found with this id");
        return loans;
    }

    public Loan getByLoanNumber(Long loanNumberLong) {
        return this.loanRepo.findByLoanNumber(loanNumberLong);
    }

    public Loan getLoanByValue(String filter) {
        return loanRepo.findByValue(filter);
    }

    public LoanResponse getAllLoans(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = null;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Loan> pageLoan = this.loanRepo.findAll(pageable);
        List<Loan> allLoans = pageLoan.getContent();

        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setContent(allLoans);
        loanResponse.setTotalCount(loanRepo.count());
        loanResponse.setPageNumber(pageLoan.getNumber());
        loanResponse.setPageSize(pageLoan.getSize());
        loanResponse.setTotalElements(pageLoan.getNumberOfElements());
        loanResponse.setTotalPages(pageLoan.getTotalPages());
        loanResponse.setLastPage(pageLoan.isLast());

        return loanResponse;
    }

}
