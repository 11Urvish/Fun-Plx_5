package com.fundplex.mainrestapi.loanTransfer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransferRepo extends JpaRepository<LoanTransfer, Long> {

    public Optional<LoanTransfer> findById(Long id);

}