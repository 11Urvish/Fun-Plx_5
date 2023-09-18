package com.fundplex.mainrestapi.rolePermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fundplex.mainrestapi.Model.RolePermission;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Autowired
    public RolePermissionService rolePermissionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody RolePermission rolePermission) {
        this.rolePermissionService.createRolePermission(rolePermission);
        return new ResponseEntity<>(new ApiResponse("RolePermission Created Successfully!!!", rolePermission, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllRolePermissions() {
        List<RolePermission> rolePermissions = this.rolePermissionService.getAllRolePermissions();
        return new ResponseEntity(new ApiResponse("List of RolePermissions!!", rolePermissions, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getRolePermissionById(@PathVariable("id") Long id) {
        Optional<RolePermission> rolePermission = this.rolePermissionService.getRolePermissionById(id);
        return new ResponseEntity(
                new ApiResponse("RolePermission Details Updated Successfully!!", rolePermission, true), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateRolePermission(@RequestBody RolePermission rolePermission) {
        this.rolePermissionService.updateRolePermission(rolePermission, rolePermission.id);
        return new ResponseEntity(
                new ApiResponse("RolePermission Details Updated Successfully!!", rolePermission, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRolePermission(@PathVariable("id") Long id) {
        this.rolePermissionService.deleteRolePermission(id);
        return new ResponseEntity(new ApiResponse("RolePermission Details Updated Successfully!!", null, true),
                HttpStatus.OK);
    }
}
