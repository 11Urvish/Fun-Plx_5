package com.fundplex.mainrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.dao.UserRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.response.UserResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;

    // @Autowired
    // public PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        this.userRepo.save(user);
    }

    public void updateUser(User user, Long id) {
        this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        user.setId(id);
        this.userRepo.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", id));
        this.userRepo.deleteById(id);
    }

    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = null;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> pageUser = this.userRepo.findAll(pageable);
        List<User> allUsers = pageUser.getContent();

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(allUsers);
        userResponse.setTotalCount(userRepo.count());
        userResponse.setPageNumber(pageUser.getNumber());
        userResponse.setPageSize(pageUser.getSize());
        userResponse.setTotalElements(pageUser.getNumberOfElements());
        userResponse.setTotalPages(pageUser.getTotalPages());
        userResponse.setLastPage(pageUser.isLast());

        return userResponse;
    }

    public User getUserById(Long id) {
        return this.userRepo.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public User existsByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    public void registerUser(User user) {
        this.userRepo.save(user);
    }

    public User existsByMobile(String mobile) {
        return this.userRepo.findByMobile(mobile);
    }

    public void updateUserProfile(User user, Long id) {
        this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        user.setId(id);
        this.userRepo.save(user);
    }

    public void changePassword(Long id, String oldPassword, String newPassword) throws Exception {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        if (!passwordEncoder().matches(oldPassword, user.getPassword())) {
            throw new Exception("Old password is incorrect");
        }

        user.setPassword(passwordEncoder().encode(newPassword));
        userRepo.save(user);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
