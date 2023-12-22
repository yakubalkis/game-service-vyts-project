package com.game.server.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
@Service
public class EmailServiceImpl implements EmailService{
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javamailsender;
    @Override
    public String sendMail(String to, String cc, String subject, String body) {
        // TODO Auto-generated method s
        try {
            MimeMessage mimeMessage=javamailsender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            javamailsender.send(mimeMessage);
            System.out.println("gönderildi");
            return "mail send";

        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }



    }

}
