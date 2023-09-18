package com.fundplex.mainrestapi.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.country.CountryRepo;
import com.fundplex.mainrestapi.dao.CompanyRepo;
import com.fundplex.mainrestapi.dao.CustomerRepo;
import com.fundplex.mainrestapi.dao.RoleRepo;
import com.fundplex.mainrestapi.dao.UserRepo;
import com.fundplex.mainrestapi.firm.FirmRepo;
import com.fundplex.mainrestapi.loan.LoanRepo;
import com.fundplex.mainrestapi.product.ProductRepo;
import com.fundplex.mainrestapi.rolePermission.RolePermissionRepo;
import com.fundplex.mainrestapi.state.StateRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private FirmRepo firmRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RolePermissionRepo rolePermissionRepo;

    public List<Object> getAllData(String lookup) {

        List<Object> data = new ArrayList<>();

        switch (lookup) {

            case "user":
                data.addAll(userRepo.findAll());
                break;
            case "role":
                data.addAll(roleRepo.findAll());
                break;
            case "customer":
                data.addAll(customerRepo.findAll());
                break;
            case "company":
                data.addAll(companyRepo.findAll());
                break;
            case "loan":
                data.addAll(loanRepo.findAll());
                break;
            case "product":
                data.addAll(productRepo.findAll());
                break;
            case "firm":
                data.addAll(firmRepo.findAll());
                break;
            case "state":
                data.addAll(stateRepo.findAll());
                break;
            case "country":
                data.addAll(countryRepo.findAll());
                break;
            case "rolePermission":
                data.addAll(rolePermissionRepo.findAll());
                break;

        }

        return data;
    }
}