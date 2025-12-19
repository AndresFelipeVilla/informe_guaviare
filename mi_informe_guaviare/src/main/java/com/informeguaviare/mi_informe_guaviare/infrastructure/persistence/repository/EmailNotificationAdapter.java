package com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.domain.port.out.EmailNotificationPort;
import com.informeguaviare.mi_informe_guaviare.infrastructure.email.PasswordResetEmailTemplate;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.nio.charset.StandardCharsets;

@Repository
public class EmailNotificationAdapter implements EmailNotificationPort {

    private final JavaMailSender javaEmailSender;
    private final PasswordResetEmailTemplate passwordResetEmailTemplate;

    @Value("${email.from:no-reply@mi-informe-guaviare.com}")
    private String from;

    @Value("${email.subject.password-reset:Restablecer contraseña}")
    private String subject;

    @Value("${app.frontend.url:http://localhost:8081}")
    private String frontendUrl;

    public EmailNotificationAdapter(JavaMailSender javaEmailSender,
            PasswordResetEmailTemplate passwordResetEmailTemplate) {
        this.javaEmailSender = javaEmailSender;
        this.passwordResetEmailTemplate = passwordResetEmailTemplate;
    }

    @Override
    public void sendPasswordResetEmail(User user, String resetToken) {
        try {
            MimeMessage message = javaEmailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setFrom(from);
            helper.setTo(user.getEmail().getValue());
            helper.setSubject(subject);

            String resetUrl = frontendUrl + "/auth/reset-password?token=" + resetToken;

            String htmlContent = passwordResetEmailTemplate.createResetPasswordEmail(user.getName(), resetUrl);

            helper.setText(htmlContent, true);
            ClassPathResource imageResource = new ClassPathResource("static/images/Logo.png");

            if (imageResource.exists()) {
                helper.addInline("logoImage", imageResource);
            } else {
                System.err.println("ADVERTENCIA: No se encontró Logo.png en resources/static/images/");
            }

            javaEmailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico: " + e.getMessage(), e);
        }
    }
}
