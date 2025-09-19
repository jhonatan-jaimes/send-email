package com.jejo.mails.implement;

import com.jejo.mails.dto.EmailDto;
import com.jejo.mails.dto.Message;
import com.jejo.mails.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailImplement implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailImplement(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public Message sendEmail(EmailDto emailDto) throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(emailDto.to());
            helper.setSubject(emailDto.subject());

            Context context = new Context();
            context.setVariable("tittle", emailDto.tittle());
            context.setVariable("message", emailDto.message());
            context.setVariable("contact", emailDto.contact());
            context.setVariable("page", emailDto.page());
            context.setVariable("email", emailDto.email());

            String contentHtml = templateEngine.process("email", context);

            helper.setText(contentHtml, true);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException("Fallo el envio: " + e.getMessage());
        }
        return new Message("Enviado satisfactoriamente");
    }
}

