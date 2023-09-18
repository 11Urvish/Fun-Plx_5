package com.fundplex.mainrestapi.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.exceptions.ApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lookup")
public class LookupController {

    @Autowired
    private LookupService lookupService;

    @PostMapping("/find")
    public ResponseEntity<ApiResponse> getAllData(@RequestBody List<Lookup> lookups) {

        // List<Object> combinedData = new ArrayList<>();
        Map<String, List<Object>> responseMap = new HashMap<>();

        // Object obj = {
        // user:List<Object>;
        // }

        for (int i = 0; i < lookups.toArray().length; i++) {

            System.out.println("For loop executing");
            System.out.println(lookups.get(i).lookup);

            if (lookups.get(i).lookup.equals("customer")) {
                System.out.println("customer lookup matched");

                // combinedData.addAll(lookupService.getAllData("customer"));
                responseMap.put("customer", lookupService.getAllData("customer"));
            }
            if (lookups.get(i).lookup.equals("user")) {
                System.out.println("user lookup matched");
                // combinedData.addAll(lookupService.getAllData("user"));
                responseMap.put("user", lookupService.getAllData("user"));
            }
            if (lookups.get(i).lookup.equals("company")) {
                System.out.println("company lookup matched");
                // combinedData.addAll(lookupService.getAllData("company"));
                responseMap.put("company", lookupService.getAllData("company"));
            }
            if (lookups.get(i).lookup.equals("firm")) {
                System.out.println("firm lookup matched");
                // combinedData.addAll(lookupService.getAllData("firm"));
                responseMap.put("firm", lookupService.getAllData("firm"));
            }
            if (lookups.get(i).lookup.equals("product")) {
                System.out.println("product lookup matched");
                // combinedData.addAll(lookupService.getAllData("product"));
                responseMap.put("product", lookupService.getAllData("product"));
            }
            if (lookups.get(i).lookup.equals("role")) {
                System.out.println("role lookup matched");
                // combinedData.addAll(lookupService.getAllData("role"));
                responseMap.put("role", lookupService.getAllData("role"));
            }
            if (lookups.get(i).lookup.equals("state")) {
                System.out.println("state lookup matched");
                // combinedData.addAll(lookupService.getAllData("state"));
                responseMap.put("state", lookupService.getAllData("state"));
            }
            if (lookups.get(i).lookup.equals("country")) {
                System.out.println("country lookup matched");
                // combinedData.addAll(lookupService.getAllData("country"));
                responseMap.put("country", lookupService.getAllData("country"));
            }
            if (lookups.get(i).lookup.equals("rolePermission")) {
                System.out.println("rolePermission lookup matched");
                // combinedData.addAll(lookupService.getAllData("rolePermission"));
                responseMap.put("rolePermission", lookupService.getAllData("rolePermission"));
            }

        }
        return new ResponseEntity<>(new ApiResponse("Data fetch Successfully", responseMap, true), HttpStatus.OK);
    }
}
