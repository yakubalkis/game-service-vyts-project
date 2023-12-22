package com.game.server.service;

public interface EmailService {
    String sendMail(String to, String cc, String subject, String body);
}
