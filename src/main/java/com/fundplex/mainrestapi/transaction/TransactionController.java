package com.fundplex.mainrestapi.transaction;

import java.util.List;
// import java.util.Optional;

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
// import com.fundplex.mainrestapi.loan.Loan;
// import com.fundplex.mainrestapi.loan.LoanService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")

public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody Transaction transaction) {
        this.transactionService.createTransaction(transaction);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Transaction Added Successfully!!", transaction, true),
                HttpStatus.CREATED);
    }

    // @PostMapping("/update")
    // public ResponseEntity<ApiResponse> updateTransaction(@RequestBody Transaction
    // transaction) {
    // this.transactionService.updateTransaction(transaction, transaction.id);
    // return new ResponseEntity<>(new ApiResponse("Transaction Updated
    // Successfully!!!", transaction, true),
    // HttpStatus.OK);
    // }

    // @PostMapping("/delete/{id}")
    // public ResponseEntity<ApiResponse> deleteTransaction(@PathVariable("id") Long
    // id) {
    // this.transactionService.deleteTransaction(id);
    // return new ResponseEntity<>(new ApiResponse("Transaction Deleted
    // Successfully!!!", null, true), HttpStatus.OK);
    // }

    // @PostMapping("/findAll")
    // public ResponseEntity<ApiResponse> getAllTransactions() {
    // List<Transaction> transactions =
    // this.transactionService.getAllTransactions();
    // return new ResponseEntity<>(new ApiResponse("List of All Transactions!!!",
    // transactions, true), HttpStatus.OK);
    // }

    // @PostMapping("/findById/{id}")
    // public ResponseEntity<ApiResponse> getTransactionById(@PathVariable("id")
    // Long id) {
    // Optional<Transaction> transaction =
    // this.transactionService.getTransactionById(id);
    // return new ResponseEntity<>(new ApiResponse("Transaction Found with this Id",
    // transaction, true),
    // HttpStatus.OK);
    // }

    @PostMapping("/findByLoanId/{id}")
    public ResponseEntity<ApiResponse> getByLoanId(@PathVariable("id") Long id) {
        List<Transaction> transactions = this.transactionService.getByLoanId(id);
        return new ResponseEntity(new ApiResponse("List of all permissions!!", transactions, true), HttpStatus.OK);
    }

}
