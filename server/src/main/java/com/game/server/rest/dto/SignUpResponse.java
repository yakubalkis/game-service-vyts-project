package com.game.server.rest.dto;

public class SignUpResponse {
    String message;
    String username;
    String role;

    public SignUpResponse() {
    }

    public SignUpResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public SignUpResponse(String message, String username, String role) {
        this.message = message;
        this.username = username;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
