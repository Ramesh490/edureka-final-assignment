package com.edureka.auth.dto;

public class AuthRequest {

    private String username;
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
