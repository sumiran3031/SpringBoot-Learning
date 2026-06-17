package com.sumiran.dependency_injection.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public String send(String message) {
        return "Email sent: " + message;
    }
}