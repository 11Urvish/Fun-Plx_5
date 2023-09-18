package com.fundplex.mainrestapi.payloads;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    // private Long id;

    // private String email;

    public Map<String, Object> data;

}
