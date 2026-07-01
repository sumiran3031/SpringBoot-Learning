package com.sumiran.email_api.service;

import com.sumiran.email_api.dto.EmailRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        message.setFrom("sumiranpaparkar@gmail.com");

        mailSender.send(message);
        return "Simple email sent to: " + request.getTo();
    }

    public String sendHtmlEmail(EmailRequest request)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        helper.setFrom("sumiranpaparkar@gmail.com");

        String htmlContent = """
                <html>
                <body>
                    <h2 style='color: #2E86AB;'>Hello from Spring Boot! 🚀</h2>
                    <p>%s</p>
                    <hr>
                    <p style='color: gray; font-size: 12px;'>
                        Sent by Sumiran Paparkar — Spring Boot Learning Journey
                    </p>
                </body>
                </html>
                """.formatted(request.getBody());

        helper.setText(htmlContent, true); // true = HTML content hai
        mailSender.send(message);

        return "HTML email sent to: " + request.getTo();
    }

    public String sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Our Platform! 🎉");
        message.setText("""
                Hi %s,

                Welcome to our platform!

                We're excited to have you on board.

                Best regards,
                Sumiran Paparkar
                Spring Boot Learning Journey
                """.formatted(name));
        message.setFrom("sumiranpaparkar@gmail.com");

        mailSender.send(message);
        return "Welcome email sent to: " + toEmail;
    }
}