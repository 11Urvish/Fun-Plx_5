package com.fundplex.mainrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.dao.UserRepo;
import com.fundplex.mainrestapi.exceptions.ResourceNotFoundException;
import com.fundplex.mainrestapi.payloads.UserDetailsImpl;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // loading user from database
        User user = this.userRepo.findUserByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email: " +
                        username, (long) 0));
        return UserDetailsImpl.build(user);

    }

}
