package com.fundplex.mainrestapi.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundplex.mainrestapi.loan.Loan;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    public Optional<Transaction> findById(Long id);

    public List<Transaction> findByLoanId(Loan loanId);

}