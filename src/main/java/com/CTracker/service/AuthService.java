package com.CTracker.service;

import com.CTracker.config.ConnectionPropertiesComponent;
import com.CTracker.dto.AuthenticationResponse;
import com.CTracker.dto.LoginRequest;
import com.CTracker.dto.RefreshTokenRequest;
import com.CTracker.dto.RegisterRequest;
import com.CTracker.exceptions.SpringRedditException;
import com.CTracker.model.NotificationEmail;
import com.CTracker.model.User;
import com.CTracker.model.VerificationToken;
import com.CTracker.repository.UserRepository;
import com.CTracker.repository.VerificationTokenRepository;
import com.CTracker.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final ConnectionPropertiesComponent connectionPropertiesComponent;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
            mailService.sendMail(new NotificationEmail("Please Activate your Account",
                    user.getEmail(), "Thank you for signing up to CTracker, " +
                    "please click on the below url to activate your account : " +
                    connectionPropertiesComponent.allowedEndpoint +
                    "/api/auth/accountVerification/" +
                    token));
    }
//    On whichever method you declare @Transactional the boundary of transaction starts and boundary ends when method completes.
//    If you are using JPA call then all commits are with in this transaction boundary.
//    essentially if you mark @transactional spring will roll back the transaction if an exception occurs
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public void resetEmail(String email){
        User user = userRepository.findByEmail(email);
        log.info("User Pulled: "+user+ " Using email:  "+ email);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail(
                "Ride Tally Password Reset Email for "+ user.getUsername(),
                user.getEmail(), "Hey YOU, did you want to reset your password?!?! " +
                "If so please click on the below url to reset your password : " +
                connectionPropertiesComponent.allowedOriginSubnet +
                "/setPassword/" +
                token));
    }
    public User changePassword(String validationToken, String password) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(validationToken);
        return fetchUserAndSetPassword(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")),passwordEncoder.encode(password));
    }
    private User fetchUserAndSetPassword(VerificationToken verificationToken, String password) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        verificationTokenRepository.delete(verificationToken);
        user.setPassword(password);
        return userRepository.save(user);
    }
}