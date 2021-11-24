package com.CTracker.controller;

import com.CTracker.dto.AuthenticationResponse;
import com.CTracker.dto.LoginRequest;
import com.CTracker.dto.RefreshTokenRequest;
import com.CTracker.dto.RegisterRequest;
import com.CTracker.service.AuthService;
import com.CTracker.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @PostMapping("/resetEmail")
    public ResponseEntity<String> resetEmail(@RequestBody String email) {
        authService.resetEmail(email);
        return new ResponseEntity<>("Password Reset Email Sent", OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @PostMapping("/password/set/{token}")
    public ResponseEntity<String> refreshTokens(@PathVariable String token,  @RequestBody String password) {
        return ResponseEntity.status(OK).body("Password Reset successfully for user: " + authService.changePassword(token,password).getUsername());
    }
}
