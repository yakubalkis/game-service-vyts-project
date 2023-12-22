package com.game.server.request;

public class EmailRequest {
    public String to;
    public String cc;
    public String subject;
    public String body;

    public EmailRequest(String to, String cc,String subject,String body) {
        this.to=to;
        this.cc=cc;
        this.subject=subject;
        this.body=body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
