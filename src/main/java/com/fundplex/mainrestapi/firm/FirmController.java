package com.fundplex.mainrestapi.firm;

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
import com.fundplex.mainrestapi.response.FirmResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/firm")

public class FirmController {

    @Autowired
    public FirmService firmService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createFirm(@RequestBody Firm firm) {
        this.firmService.createFirm(firm);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Firm Added Successfully!!", firm, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateFirm(@RequestBody Firm firm) {
        this.firmService.updateFirm(firm, firm.id);
        return new ResponseEntity<>(new ApiResponse("Firm Updated Successfully!!!", firm, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFirm(@PathVariable("id") Long id) {
        this.firmService.deleteFirm(id);
        return new ResponseEntity<>(new ApiResponse("Firm Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllFirms(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {

        FirmResponse firmResponse = this.firmService.getAllFirms(pageNumber, pageSize, sortBy,
                sortDirection);
        return new ResponseEntity<>(new ApiResponse("List of Firms", firmResponse, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getFirmById(@PathVariable("id") Long id) {
        Optional<Firm> firm = this.firmService.getFirmById(id);
        return new ResponseEntity<>(new ApiResponse("Firm Found with this Id", firm, true), HttpStatus.OK);
    }

    @PostMapping("/findByValue")
    public ResponseEntity<ApiResponse> getFirmByValue(@RequestParam("filter") String filter) {
        List<Firm> firms = firmService.getFirmByValue(filter);
        return new ResponseEntity<>(new ApiResponse("List of All Firms!!!", firms, true), HttpStatus.OK);
    }

}
