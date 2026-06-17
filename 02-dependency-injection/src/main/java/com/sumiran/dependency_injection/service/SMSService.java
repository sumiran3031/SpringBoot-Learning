package com.sumiran.dependency_injection.service;

import org.springframework.stereotype.Service;

@Service
public class SMSService {

    public String send(String message) {
        return "SMS sent: " + message;
    }
}