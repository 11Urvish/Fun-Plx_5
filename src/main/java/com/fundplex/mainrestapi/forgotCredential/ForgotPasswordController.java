package com.fundplex.mainrestapi.forgotCredential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.services.UserService;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/auth")

public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {

        User user = userService.getUserByEmail(request.getEmail());
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse("Invalid password reset token", null, false),
                    HttpStatus.NOT_FOUND);
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(new Date(System.currentTimeMillis() + 3600000));

        passwordResetTokenRepository.save(token);

        String resetLink = "http://localhost:8585/api/auth/reset-password?token=" + token.getToken();

        emailService.sendPasswordResetEmail(user.getEmail(), "Reset Password Link!!!", resetLink);

        return new ResponseEntity<>(new ApiResponse("Reset Link sent to your Email", null, true), HttpStatus.OK);
    }

}
