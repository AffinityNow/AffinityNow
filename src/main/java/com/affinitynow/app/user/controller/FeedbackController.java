package com.affinitynow.app.user.controller;

import com.affinitynow.app.model.Mail;
import com.affinitynow.app.mail.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private EmailService emailService;

    public FeedbackController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Mail feedback){
        this.emailService.sendMail(feedback);
    }


}
