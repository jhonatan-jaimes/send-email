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
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(emailDto.to());
            helper.setSubject(emailDto.subject());
            // helper.setText(emailDto.message()); // <-- Texto plano en el correo electronico

            Context context = new Context();
            context.setVariable("message", emailDto.message());

            String contentHtml = templateEngine.process("email", context);

            helper.setText(contentHtml, true);
        } catch (Exception e){
            throw new RuntimeException("Fallo el envio" + e);
        }
        return new Message("Enviado satisfactoriamente");
    }
}
