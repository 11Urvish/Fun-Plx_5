package com.fundplex.mainrestapi.forgotCredential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.exceptions.ApiResponse;
import com.fundplex.mainrestapi.services.UserService;

@RestController
@RequestMapping("/auth")
public class ResetPasswordController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetPasswordRequest request) {

        PasswordResetToken token = passwordResetTokenRepository.findByToken(request.getToken());
        if (token == null) {
            return new ResponseEntity<>(new ApiResponse("Invalid password reset token", null,
                    false), HttpStatus.NOT_FOUND);
        }

        if (token.getExpiryDate().getTime() - System.currentTimeMillis() <= 0) {
            return new ResponseEntity<>(new ApiResponse("Password reset token has expired", null, false),
                    HttpStatus.BAD_REQUEST);
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.createUser(user);

        passwordResetTokenRepository.delete(token);

        return new ResponseEntity<>(new ApiResponse("Password reset successfully", null, true), HttpStatus.OK);
    }
}
