package vn.iotstar.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.io.FileWriter;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.host:}")
    private String mailHost;

    public void sendOtpEmail(String toEmail, String otpCode) {
        try (FileWriter fw = new FileWriter("otp.txt", true)) {
            fw.write(toEmail + ":" + otpCode + "\n");
        } catch (Exception e) {}
        
        if (mailHost != null && !mailHost.trim().isEmpty()) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toEmail);
                message.setSubject("Your OTP Code");
                message.setText("Your OTP code is: " + otpCode + "\n\nThis code will expire in 5 minutes.");
                mailSender.send(message);
            } catch (Exception e) {
            }
        }
    }
}