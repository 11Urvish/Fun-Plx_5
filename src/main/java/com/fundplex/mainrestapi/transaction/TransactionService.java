package com.fundplex.mainrestapi.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.loan.Loan;
import com.fundplex.mainrestapi.loan.LoanRepo;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepo transactionRepo;
    @Autowired
    public LoanRepo loanRepo;

    public void createTransaction(Transaction transaction) {
        this.transactionRepo.save(transaction);
    }

    public void updateTransaction(Transaction transaction, Long id) {
        this.transactionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", id));
        transaction.setId(id);
        this.transactionRepo.save(transaction);
    }

    public void deleteTransaction(Long id) {
        this.transactionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "Id", id));
        this.transactionRepo.deleteById(id);
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepo.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return this.transactionRepo.findById(id);
    }

    public List<Transaction> getByLoanId(Long id) {
        Loan loan = this.loanRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
        System.out.println("Loan found with this id");
        List<Transaction> transactions = this.transactionRepo.findByLoanId(loan);
        System.out.println("permissions find found with this id");
        return transactions;
    }

}
