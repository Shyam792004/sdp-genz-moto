package com.vhub.v1.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vhub.v1.dto.request.LoginRequest;
import com.vhub.v1.dto.request.RegisterRequest;
import com.vhub.v1.dto.response.AuthResponse;
import com.vhub.v1.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Make sure this matches your frontend URL
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final AuthService authService; // Use the interface instead of implementation

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Allows users to register by providing necessary registration details.")
    public ResponseEntity<AuthResponse> register(
            @Parameter(description = "Registration details of the user") @RequestBody RegisterRequest registerRequest) {

        // Service will handle the registration logic and return a response with
        // success/failure status
        AuthResponse response = authService.register(registerRequest);

        // Return the response from the service
        return new ResponseEntity<>(response, OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Allows users to authenticate by providing valid login credentials.")
    public ResponseEntity<AuthResponse> login(
            @Parameter(description = "Login credentials of the user") @RequestBody LoginRequest loginRequest) {

        // Service will handle the authentication logic
        AuthResponse response = authService.authenticate(loginRequest);

        // Return the response from the service
        return new ResponseEntity<>(response, OK);
    }
}
