package com.fundplex.mainrestapi.loanTransfer;

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
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loanTransfer")

public class LoanTransferController {

    @Autowired
    public LoanTransferService loanTransferService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createLoanTransfer(@RequestBody LoanTransfer loanTransfer) {
        this.loanTransferService.createLoanTransfer(loanTransfer);
        return new ResponseEntity<ApiResponse>(new ApiResponse("LoanTransfer Added Successfully!!", loanTransfer, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateLoanTransfer(@RequestBody LoanTransfer loanTransfer) {
        this.loanTransferService.updateLoanTransfer(loanTransfer, loanTransfer.id);
        return new ResponseEntity<>(new ApiResponse("LoanTransfer Updated Successfully!!!", loanTransfer, true),
                HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLoanTransfer(@PathVariable("id") Long id) {
        this.loanTransferService.deleteLoanTransfer(id);
        return new ResponseEntity<>(new ApiResponse("LoanTransfer Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllLoanTransfers() {
        List<LoanTransfer> loanTransfers = this.loanTransferService.getAllLoanTransfers();
        return new ResponseEntity<>(new ApiResponse("List of All LoanTransfers!!!", loanTransfers, true),
                HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getLoanTransferById(@PathVariable("id") Long id) {
        Optional<LoanTransfer> loanTransfer = this.loanTransferService.getLoanTransferById(id);
        return new ResponseEntity<>(new ApiResponse("LoanTransfer Found with this Id", loanTransfer, true),
                HttpStatus.OK);
    }

}
