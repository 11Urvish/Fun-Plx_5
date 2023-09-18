package com.fundplex.mainrestapi.morgageItems;

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
@RequestMapping("/morgageitems")
public class MorgageItemsController {
    @Autowired
    public MorgageItemsService morgageItemsService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMorgageItems(@RequestBody MorgageItems morgageItems) {
        this.morgageItemsService.createMorgageItems(morgageItems);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Role Added Successfully!!", morgageItems, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateMorgageItems(@RequestBody MorgageItems morgageItems) {
        this.morgageItemsService.updateMorgageItems(morgageItems, morgageItems.id);
        return new ResponseEntity<>(new ApiResponse("MorgageItems Updated Successfully!!!", morgageItems, true),
                HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMorgageItems(@PathVariable("id") Long id) {
        this.morgageItemsService.deleteMorgageItems(id);
        return new ResponseEntity<>(new ApiResponse("Customer Deleted Successfully!!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllMorgageItemss() {
        List<MorgageItems> morgageItemss = this.morgageItemsService.getAllMorgageItemss();
        return new ResponseEntity<>(new ApiResponse("List of All Customers!!!", morgageItemss, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getMorgageItemsById(@PathVariable("id") Long id) {
        Optional<MorgageItems> morgageItems = this.morgageItemsService.getMorgageItemsById(id);
        return new ResponseEntity<>(new ApiResponse("Customer Found with this Id", morgageItems, true), HttpStatus.OK);
    }

}
