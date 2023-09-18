package com.fundplex.mainrestapi.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PasswordChangeRequest {

    public Long id;

    public String oldPassword;

    public String newPassword;

}
