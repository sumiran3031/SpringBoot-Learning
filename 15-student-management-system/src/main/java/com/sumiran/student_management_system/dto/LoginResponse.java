package com.sumiran.student_management_system.dto;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) { this.token = token; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}