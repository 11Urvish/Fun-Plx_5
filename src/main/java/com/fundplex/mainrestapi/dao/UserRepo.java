package com.fundplex.mainrestapi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundplex.mainrestapi.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    public User getUserById(Long id);

    public Optional<User> findUserByEmail(String email);

    public User findByEmail(String email);

    public User findByMobile(String mobile);

}
