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


    public void sendResetPasswordEmail(String to, String resetUrl) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String logoUrl = "https://soacwaaslearning.studiolab.fr/assets/SEMSOAC E LEARNING.png"; // URL de votre logo
            String htmlContent = "<html>"
                    + "<body>"
                    + "<div style='text-align: center;'>"
                    + "<img src='" + logoUrl + "' alt='Logo' style='width: 150px; margin-bottom: 20px;'/>"
                    + "<h2>Réinitialisation de votre mot de passe</h2>"
                    + "<p>Bonjour,</p>"
                    + "<p>Vous avez demandé à réinitialiser votre mot de passe. Cliquez sur le bouton ci-dessous pour créer un nouveau mot de passe :</p>"
                    + "<a href='" + resetUrl + "' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #007bff; border-radius: 5px; text-decoration: none;'>Réinitialiser le mot de passe</a>"
                    + "<p>Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet e-mail.</p>"
                    + "<p>Cordialement,</p>"
                    + "<p>Votre équipe de support</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setTo(to);
            helper.setSubject("Réinitialisation de votre mot de passe");
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            // Gérer les exceptions d'envoi de mail
        }
    }
}
