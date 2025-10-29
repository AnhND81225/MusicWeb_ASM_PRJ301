/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author phant
 */
public class OTPUtil {

    public static void sendEmail(String toEmail, String subject, String body) {
        final String fromEmail = "phanthanhdat2505@gmail.com";
        final String password = "nvjw dpeb zxxm rqlx"; // dùng App Password, không dùng mật khẩu thường

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail, "LoveMusic"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String verifyOTP(String otpSend, String otpConfirm, LocalDateTime sendAt, LocalDateTime confirmAt) {
        if (otpSend == null || otpConfirm == null || sendAt == null || confirmAt == null) {
            return "OTP is empty!";
        }

        Duration duration = Duration.between(sendAt, confirmAt);
        long minutes = duration.toMinutes();
        if (minutes > 5) {
            System.out.println("OTP đã hết hạn!.");
            return "OTP expired";
        }

        if (!otpSend.equals(otpConfirm)) {
            System.out.println("Mã OTP không chính xác.");
            return "Wrong OTP";
        }

        return "Ok";

    }
}
