package com.fundplex.mainrestapi.loanTransfer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

@Service
public class LoanTransferService {
    @Autowired
    public LoanTransferRepo loanTransferRepo;

    public void createLoanTransfer(LoanTransfer loanTransfer) {
        this.loanTransferRepo.save(loanTransfer);
    }

    public void updateLoanTransfer(LoanTransfer loanTransfer, Long id) {
        this.loanTransferRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanTransfer", "Id", id));
        loanTransfer.setId(id);
        this.loanTransferRepo.save(loanTransfer);
    }

    public void deleteLoanTransfer(Long id) {
        this.loanTransferRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanTransfer", "Id", id));
        this.loanTransferRepo.deleteById(id);
    }

    public List<LoanTransfer> getAllLoanTransfers() {
        return this.loanTransferRepo.findAll();
    }

    public Optional<LoanTransfer> getLoanTransferById(Long id) {
        return this.loanTransferRepo.findById(id);
    }

}
