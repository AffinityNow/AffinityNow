package com.affinitynow.app.user.controller;

import com.affinitynow.app.model.Feedback;
import com.affinitynow.app.user.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private EmailService emailService;

    public FeedbackController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendFeddback(@RequestBody Feedback feedback, BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid");
        }
        //envoie de mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailService.getHost());
        mailSender.setPort(this.emailService.getPort());
        mailSender.setUsername(this.emailService.getUsername());
        mailSender.setPassword(this.emailService.getPassword());
        //creation d'un email d'instance
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("smarttestdevops@gmail.com");
        mailMessage.setSubject("New feedback from"+feedback.getName());
        mailMessage.setText(feedback.getFeedback());
        //envoie de mail
        mailSender.send(mailMessage);

    }

}
