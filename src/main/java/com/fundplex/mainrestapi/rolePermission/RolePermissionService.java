package com.fundplex.mainrestapi.rolePermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.RolePermission;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class RolePermissionService {
    @Autowired
    public RolePermissionRepo rolePermissionRepo;

    public void createRolePermission(RolePermission rolePermission) {
        this.rolePermissionRepo.save(rolePermission);
    }

    public void updateRolePermission(RolePermission rolePermission, Long id) {
        this.rolePermissionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        rolePermission.setId(rolePermission.getId());
        this.rolePermissionRepo.save(rolePermission);
    }

    public void deleteRolePermission(Long id) {
        this.rolePermissionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.rolePermissionRepo.deleteById(id);
    }

    public List<RolePermission> getAllRolePermissions() {
        return this.rolePermissionRepo.findAll();
    }

    public Optional<RolePermission> getRolePermissionById(Long id) {
        return this.rolePermissionRepo.findById(id);
    }
}
