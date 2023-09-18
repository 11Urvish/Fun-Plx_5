package com.fundplex.mainrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.Role;
import com.fundplex.mainrestapi.Model.RolePermission;
import com.fundplex.mainrestapi.dao.RoleRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.rolePermission.RolePermissionRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public RolePermissionRepo rolePermissionRepo;

    public void createRole(Role role) {
        this.roleRepo.save(role);
    }

    public void updateRole(Role role, Long id) {
        this.roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        role.setId(role.getId());
        this.roleRepo.save(role);
    }

    public void deleteRole(Long id) {
        this.roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.roleRepo.deleteById(id);
    }

    public List<Role> getAllRoles() {
        return this.roleRepo.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return this.roleRepo.findById(id);
    }

    public List<RolePermission> getPermissionsByRoleId(Long id) {
        Role role = this.roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
        System.out.println("Role found with this id");
        List<RolePermission> permissions = this.rolePermissionRepo.findByRoleId(role);
        System.out.println("permissions find found with this id");
        return permissions;
    }
}
