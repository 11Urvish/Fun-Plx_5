package com.fundplex.mainrestapi.rolePermission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundplex.mainrestapi.Model.Role;
import com.fundplex.mainrestapi.Model.RolePermission;

public interface RolePermissionRepo extends JpaRepository<RolePermission, Long> {

    public List<RolePermission> findByRoleId(Role roleId);

}
