package com.edureka.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AuthRequest {

	@NotBlank
    private String username;
	@NotEmpty
    private String role;

    public AuthRequest() {}

    public String getUsername() { return username; }
    public String getRole() { return role; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
