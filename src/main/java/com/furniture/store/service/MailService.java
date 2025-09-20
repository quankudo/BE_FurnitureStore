package com.furniture.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public void sendActivationEmail(String toEmail, String token) {
        String subject = "Kích hoạt tài khoản của bạn";
        String activationLink = "http://localhost:8080/api/v1.0/auth/activate?token=" + token;

        String content = "Xin chào,\n\n"
                + "Vui lòng nhấn vào link dưới đây để kích hoạt tài khoản của bạn:\n"
                + activationLink + "\n\n"
                + "Cảm ơn!";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);

            log.info("Đã gửi email kích hoạt đến {}", toEmail);
        } catch (Exception e) {
            log.error("Lỗi khi gửi mail kích hoạt đến {}: {}", toEmail, e.getMessage());
            throw new RuntimeException("Không thể gửi email kích hoạt");
        }
    }
}
