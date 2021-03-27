package com.affinitynow.app.mail;
import com.affinitynow.app.mail.MailManager;
import com.affinitynow.app.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
@Autowired
private MailManager mailManager ;

    public MailManager getMailManager() {
        return mailManager;
    }

    public SimpleMailMessage createMail(String from, String to, String body, String subject){
        return mailManager.createMailMessage(new Mail(from, to, body, subject));
    }

    public void sendMail(Mail feedback){
        SimpleMailMessage mailToSend = this.createMail(feedback.getFrom(), feedback.getTo(), feedback.getBody(), feedback.getSubject());
        JavaMailSenderImpl mailSender= this.createMailSender();
        mailSender.send(mailToSend);
    }

    public JavaMailSenderImpl createMailSender(){
        return mailManager.createMailSender();
    }

}
