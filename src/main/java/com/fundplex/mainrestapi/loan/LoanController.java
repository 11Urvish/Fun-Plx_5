package com.fundplex.mainrestapi.loan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.response.LoanResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    public LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createLoan(@RequestBody Loan loan) {
        this.loanService.createLoan(loan);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Loan Details Added Successfully!!", loan, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateLoan(@RequestBody Loan loan) {
        this.loanService.updateLoan(loan, loan.id);
        return new ResponseEntity<>(new ApiResponse("Loan Details Updated Successfully!!!", loan, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLoan(@PathVariable("id") Long id) {
        this.loanService.deleteLoan(id);
        return new ResponseEntity<>(new ApiResponse("Loan Details Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllLoans(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {

        LoanResponse loanResponse = this.loanService.getAllLoans(pageNumber, pageSize, sortBy,
                sortDirection);
        return new ResponseEntity<>(new ApiResponse("List of Loans", loanResponse, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getLoanById(@PathVariable("id") Long id) {
        Optional<Loan> loan = this.loanService.getLoanById(id);
        return new ResponseEntity<>(new ApiResponse("Loan Details Found with this Id", loan, true), HttpStatus.OK);
    }

    @PostMapping("/findByCustomerId/{id}")
    public ResponseEntity<ApiResponse> getByCustomerId(@PathVariable("id") Long id) {
        List<Loan> loans = this.loanService.getByCustomerId(id);
        return new ResponseEntity(new ApiResponse("List of all permissions!!", loans, true), HttpStatus.OK);
    }

    @PostMapping("/findLoanNumber")
    public ResponseEntity<ApiResponse> getLoanNumber(@RequestParam("loanNumber") String loanNumber) {
        Long loanNumberLong = Long.valueOf(loanNumber);
        Loan loan = this.loanService.getByLoanNumber(loanNumberLong);
        Long getLoanNumber = loan.getLoanNumber();
        return new ResponseEntity<>(new ApiResponse("Loan Details Found with this Id", getLoanNumber, true),
                HttpStatus.OK);
    }

    @PostMapping("/findByValue")
    public ResponseEntity<ApiResponse> getLoanByValue(@RequestParam("filter") String filter) {
        Loan loan = loanService.getLoanByValue(filter);
        return new ResponseEntity<>(new ApiResponse("List of All Loans!!!", loan, true), HttpStatus.OK);
    }

}
