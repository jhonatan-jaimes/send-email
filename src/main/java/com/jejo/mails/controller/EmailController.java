package com.jejo.mails.controller;

import com.jejo.mails.dto.EmailDto;
import com.jejo.mails.dto.Message;
import com.jejo.mails.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendEmail(@RequestBody EmailDto emailDto) throws MessagingException {
        return ResponseEntity.ok(emailService.sendEmail(emailDto));
    }
}
