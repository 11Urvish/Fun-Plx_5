package com.fundplex.mainrestapi.loan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fundplex.mainrestapi.Model.Customer;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    public Optional<Loan> findById(Long id);

    public List<Loan> findByCustomerId(Customer customerId);

    public List<Loan> findByCustomerId(Long customerId);

    public Loan findByLoanNumber(Long loanNumber);

    @Query("SELECT l FROM Loan l WHERE lower(l.loanNumber) LIKE lower(concat('%', :keyword, '%'))")
    Loan findByValue(@Param("keyword") String keyword);

}
