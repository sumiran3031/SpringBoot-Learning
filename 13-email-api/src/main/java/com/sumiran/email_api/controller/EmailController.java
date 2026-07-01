package com.sumiran.email_api.controller;

import com.sumiran.email_api.dto.EmailRequest;
import com.sumiran.email_api.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendSimpleEmail(@RequestBody EmailRequest request) {
        return emailService.sendSimpleEmail(request);
    }

    @PostMapping("/send-html")
    public String sendHtmlEmail(@RequestBody EmailRequest request) {
        try {
            return emailService.sendHtmlEmail(request);
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @PostMapping("/welcome")
    public String sendWelcomeEmail(
            @RequestParam String email,
            @RequestParam String name) {
        return emailService.sendWelcomeEmail(email, name);
    }
}