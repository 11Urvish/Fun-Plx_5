package com.fundplex.mainrestapi.forgotCredential;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fundplex.mainrestapi.Model.User;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "passwordResetTokens")

public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @OneToOne
    private User user;
    private Date expiryDate;
}
