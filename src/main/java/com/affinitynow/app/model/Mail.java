package com.affinitynow.app.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Mail {

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @NotNull
    @Email
    private String from;

    @NotNull
    @Email
    private String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @NotNull
    @Min(10)
    private String body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotNull
    private String subject;

    public Mail() { }

    public Mail(@NotNull @Email String from, @NotNull @Email String to, @NotNull @Min(10) String body, @NotNull String subject) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.subject = subject;
    }
}
