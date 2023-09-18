package com.fundplex.mainrestapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundplex.mainrestapi.Model.Role;
import com.fundplex.mainrestapi.Model.RolePermission;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
