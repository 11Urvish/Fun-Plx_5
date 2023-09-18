package com.fundplex.mainrestapi.Model;

import com.fundplex.mainrestapi.payloads.UserDetailsDto;
import com.fundplex.mainrestapi.payloads.UserDetailsImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthResponse {

    String token;
    UserDetailsImpl user;
    // Long id;

    // String email;

    // String firstName;
    // String lastName;

}
