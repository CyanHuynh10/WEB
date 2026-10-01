package vn.iot.ltweb_midterm.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.InputStream;
import java.util.Properties;

public class MailUtil_24110064 {
    private static Properties appProps = new Properties();

    static {
        try {
            InputStream in = MailUtil_24110064.class.getClassLoader().getResourceAsStream("application.properties");
            if (in != null) {
                appProps.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String resolveProperty(String value) {
        if (value != null && value.startsWith("${") && value.endsWith("}")) {
            String envVar = value.substring(2, value.length() - 1);
            String envValue = System.getenv(envVar);
            return envValue != null ? envValue : value;
        }
        return value;
    }

    public static boolean sendOTP(String toEmail, String otp) {
        String host = appProps.getProperty("mail.smtp.host", "smtp.gmail.com");
        String port = appProps.getProperty("mail.smtp.port", "587");
        String user = resolveProperty(appProps.getProperty("mail.username"));
        String pass = resolveProperty(appProps.getProperty("mail.password"));

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", appProps.getProperty("mail.smtp.auth", "true"));
        props.put("mail.smtp.starttls.enable", appProps.getProperty("mail.smtp.starttls.enable", "true"));

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã OTP kích hoạt tài khoản - LTWeb_Midterm");
            
            String content = "Xin chào,\n\n"
                           + "Mã OTP của bạn là: " + otp + "\n\n"
                           + "Mã có hiệu lực trong 5 phút.\n\n"
                           + "Không chia sẻ mã OTP cho người khác.\n\n"
                           + "Trân trọng,\n"
                           + "LTWeb_Midterm";
                           
            message.setText(content);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            // Do not print sensitive info or app passwords
            System.err.println("Error sending OTP email: " + e.getMessage());
            return false;
        }
    }
}
