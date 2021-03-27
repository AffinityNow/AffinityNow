package com.affinitynow.app.mail;

import com.affinitynow.app.model.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.ValidationException;
@Component
public class MailManager {
    private static MailManager INSTANCE;
    public static MailManager getInstance(){
        if(INSTANCE==null)
            INSTANCE = new MailManager();
        return INSTANCE;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public JavaMailSenderImpl createMailSender(){

        JavaMailSenderImpl jms = new JavaMailSenderImpl();

        jms.setHost(this.getHost());
        jms.setPort(this.getPort());
        jms.setUsername(this.getUsername());
        jms.setPassword(this.getPassword());
        return jms;
    }

    public SimpleMailMessage createMailMessage(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail.getFrom());
        mailMessage.setTo(mail.getTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getBody());
        return mailMessage;

    }
}
