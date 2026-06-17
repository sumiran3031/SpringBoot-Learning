package com.sumiran.dependency_injection.controller;

import com.sumiran.dependency_injection.service.EmailService;
import com.sumiran.dependency_injection.service.SMSService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final EmailService emailService;
    private final SMSService smsService;


    public NotificationController(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @GetMapping("/notify/email")
    public String sendEmail(@RequestParam String msg) {
        return emailService.send(msg);
    }

    @GetMapping("/notify/sms")
    public String sendSMS(@RequestParam String msg) {
        return smsService.send(msg);
    }
}