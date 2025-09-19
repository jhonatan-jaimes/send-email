package com.jejo.mails.service;

import com.jejo.mails.dto.EmailDto;
import com.jejo.mails.dto.Message;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {

    Message sendEmail(EmailDto emailDto) throws MessagingException;

}
