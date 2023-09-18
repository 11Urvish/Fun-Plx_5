package com.fundplex.mainrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fundplex.mainrestapi.Model.Role;
import com.fundplex.mainrestapi.Model.RolePermission;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.services.RoleService;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Role role) {
        this.roleService.createRole(role);
        return new ResponseEntity<>(new ApiResponse("Role Created Successfully!!!", role, true),
                HttpStatus.CREATED);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllRoles() {
        List<Role> roles = this.roleService.getAllRoles();
        return new ResponseEntity(new ApiResponse("List of Roles!!", roles, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getRoleById(@PathVariable("id") Long id) {
        Optional<Role> role = this.roleService.getRoleById(id);
        return new ResponseEntity(new ApiResponse("Role Details Updated Successfully!!", role, true), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateRole(@RequestBody Role role) {
        this.roleService.updateRole(role, role.id);
        return new ResponseEntity(new ApiResponse("Role Details Updated Successfully!!", role, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable("id") Long id) {
        this.roleService.deleteRole(id);
        return new ResponseEntity(new ApiResponse("Role Details Updated Successfully!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/findPermissionsByRoleId/{id}")
    public ResponseEntity<ApiResponse> getPermissionsByRoleId(@PathVariable("id") Long id) {
        List<RolePermission> permissions = this.roleService.getPermissionsByRoleId(id);
        return new ResponseEntity(new ApiResponse("List of all permissions!!", permissions, true), HttpStatus.OK);
    }

}
