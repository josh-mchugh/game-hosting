package com.example.demo.email.service;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import com.example.demo.framework.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderService implements IEmailSenderService{

    private final AppConfig appConfig;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    public EmailSenderResponse handleEmailSend(EmailSenderRequest request) {

        Context context = new Context(Locale.getDefault(), request.getContext());
        String html = templateEngine.process(request.getTemplate().getTemplate(), context);

        try {

            MimeMessageHelper helper = new MimeMessageHelper(emailSender.createMimeMessage(), StandardCharsets.UTF_8.name());
            helper.setTo(request.getToAddress());
            //TODO: Fix so the from is persisted on email creation
            helper.setFrom(appConfig.getEmail().getNoReplyAddress());
            helper.setSubject(request.getTemplate().getSubject());
            helper.setText(html, true);

            emailSender.send(helper.getMimeMessage());

        } catch (MessagingException e) {

            log.error(String.format("Unable to send email for id: %s", request.getId()), e);

            return EmailSenderResponse.builder()
                    .id(request.getId())
                    .status(EmailStatus.FAILED)
                    .build();
        }

        return EmailSenderResponse.builder()
                .id(request.getId())
                .status(EmailStatus.SENT)
                .build();
    }
}
