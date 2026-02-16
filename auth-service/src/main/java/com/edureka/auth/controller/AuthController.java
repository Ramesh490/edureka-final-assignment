package com.edureka.auth.controller;

import com.edureka.auth.dto.AuthRequest;
import com.edureka.auth.dto.AuthResponse;
import com.edureka.auth.exception.UserException;
import com.edureka.auth.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public AuthResponse generateToken(@RequestBody AuthRequest request) {

        if (request.getUsername() == null || request.getRole() == null) {
            throw new UserException("Username and role are required");
        }

        String token = jwtService.generateToken(
                request.getUsername(),
                request.getRole()
        );

        return new AuthResponse(token);
    }
}
