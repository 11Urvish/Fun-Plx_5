package com.fundplex.mainrestapi.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fundplex.mainrestapi.Model.Role;
import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.response.UserResponse;
import com.fundplex.mainrestapi.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody User user) {
        if (userService.existsByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(new ApiResponse("Error: Email is already in use!", user.getEmail(), false),
                    HttpStatus.OK);
        }
        if (userService.existsByMobile(user.getMobile()) != null) {
            return new ResponseEntity<>(
                    new ApiResponse("Error: Mobile Number is already exist!", user.getEmail(), false),
                    HttpStatus.OK);
        }

        // user.setRoles(List.of(user.getRoles().toArray(new Role[0])));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.registerUser(user);
        return new ResponseEntity(new ApiResponse("User Registered Successfully!!!", user, true), HttpStatus.OK);
    }

    @PostMapping("/findAll")
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ascending", required = false) String sortDirection) {

        UserResponse userResponse = this.userService.getAllUsers(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(new ApiResponse("List of Users", userResponse, true), HttpStatus.OK);
    }

    @PostMapping("/findById/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("id") Long id) {
        User user = this.userService.getUserById(id);
        return new ResponseEntity<>(new ApiResponse("User Found with this Id", user, true), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user) {
        User oldUser = userService.getUserById(user.id);
        if (oldUser != null) {
            user.setPassword(oldUser.getPassword());
            // if (oldUser.getEmail() == user.getEmail()) {
            // user.setEmail(oldUser.getEmail());
            // }
            // if (oldUser.getMobile() == user.getMobile()) {
            // user.setMobile(oldUser.getMobile());
            // }
        }

        // if (userService.existsByEmail(user.getEmail()) != null) {
        // return new ResponseEntity<>(new ApiResponse("Error: Email is already in
        // use!", user.getEmail(), false),
        // HttpStatus.OK);
        // }
        // if (userService.existsByMobile(user.getMobile()) != null) {
        // return new ResponseEntity<>(
        // new ApiResponse("Error: Mobile Number is already exist!", user.getEmail(),
        // false),
        // HttpStatus.OK);
        // }

        this.userService.updateUser(user, user.id);
        return new ResponseEntity(new ApiResponse("User Details Updated Successfully!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<ApiResponse> updateUserProfile(@RequestBody User user) {
        User oldUser = userService.getUserById(user.id);
        if (oldUser != null) {
            user.setPassword(oldUser.getPassword());
            user.setRoles(oldUser.getRoles());
            user.setStatus(oldUser.getStatus());
            user.setUserType(oldUser.getUserType());

        }
        // if (userService.existsByEmail(user.getEmail()) != null) {
        // return new ResponseEntity<>(new ApiResponse("Error: Email is already in
        // use!", user.getEmail(), false),
        // HttpStatus.OK);
        // }
        // if (userService.existsByMobile(user.getMobile()) != null) {
        // return new ResponseEntity<>(
        // new ApiResponse("Error: Mobile Number is already exist!", user.getEmail(),
        // false),
        // HttpStatus.OK);
        // }
        this.userService.updateUserProfile(user, user.id);
        return new ResponseEntity(new ApiResponse("User Details Updated Successfully!!", null, true), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody PasswordChangeRequest request) throws Exception {

        userService.changePassword(request.id, request.getOldPassword(), request.getNewPassword());
        // return ResponseEntity.ok().build();
    }
}
