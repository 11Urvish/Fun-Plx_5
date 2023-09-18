package com.fundplex.mainrestapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fundplex.mainrestapi.Model.AuthRequest;
import com.fundplex.mainrestapi.Model.AuthResponse;
import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.helper.JwtUtil;
import com.fundplex.mainrestapi.payloads.UserDetailsImpl;
import com.fundplex.mainrestapi.services.CustomUserDetailService;
import com.fundplex.mainrestapi.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String test() {
        return "Welcome to Fundplex";
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> createToken(@RequestBody AuthRequest authRequest) throws Exception {

        this.authenticate(authRequest.getUsername(), authRequest.getPassword());

        UserDetailsImpl userDetails = (UserDetailsImpl) customUserDetailService
                .loadUserByUsername(authRequest.getUsername());
        User user = userService.getUserByEmail(authRequest.getUsername());
        String token = this.jwtUtil.generateToken(user);

        UserDetailsImpl data = new UserDetailsImpl();
        data.setEmail(userDetails.getEmail());
        data.setId(userDetails.getId());
        data.setFirstName(userDetails.getFirstName());
        data.setLastName(userDetails.getLastName());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(data);
        System.out.println("JWT Token is:  " + token);
        return new ResponseEntity<>(new ApiResponse("Login Successfully", authResponse, true), HttpStatus.OK);

    }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                username, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Details");
            throw new Exception("Invalid Username or Password !!!!");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody User user) {
        if (userService.existsByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(new ApiResponse("Error: Email is already in use!", user.getEmail(), false),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByMobile(user.getMobile()) != null) {
            return new ResponseEntity<>(
                    new ApiResponse("Error: Mobile Number is already exist!", user.getMobile(), false),
                    HttpStatus.BAD_REQUEST);
        }
        // user.setRoles(List.of(user.getRoles().toArray(new Role[0])));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userService.registerUser(user);
        return new ResponseEntity(new ApiResponse("User Registered Successfully!!!", user, true), HttpStatus.CREATED);
    }

}
