package com.affinitynow.app.mail.controller;

import com.affinitynow.app.model.Mail;
import com.affinitynow.app.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Mail feedback){
        this.emailService.sendMail(feedback);
    }
}
