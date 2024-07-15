package com.agrotech.api.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;



    public void sendActivateMail(String recipientEmail, String activationUrl) {
        // Implement email sending logic for activation email
        // Example:
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Activate Your Account");
        message.setText("Please click the following link to activate your account: " + activationUrl);
        javaMailSender.send(message);
    }
}
