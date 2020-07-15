package com.example.demo.email.service;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import com.example.demo.email.templates.IEmailTemplate;
import com.example.demo.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderService implements IEmailSenderService{

    private final MessageUtil messageUtil;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final Map<EmailTemplate, IEmailTemplate> emailTemplates;

    @Override
    public EmailSenderResponse handleEmailSend(EmailSenderRequest request) {

        try {

            IEmailTemplate emailTemplate = emailTemplates.get(request.getTemplate());

            String subject = messageUtil.getMessage(emailTemplate.subject(), request.getSubjectContext());

            Context context = new Context(Locale.getDefault(), request.getBodyContext());
            String body = templateEngine.process(emailTemplate.path(), context);

            MimeMessageHelper helper = new MimeMessageHelper(emailSender.createMimeMessage(), StandardCharsets.UTF_8.name());
            helper.setTo(request.getToAddress());
            helper.setFrom(emailTemplate.fromAddress());
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(helper.getMimeMessage());

            return EmailSenderResponse.builder()
                    .id(request.getId())
                    .status(EmailStatus.SENT)
                    .build();


        } catch (Exception e) {

            log.error(String.format("Unable to send email for id: %s", request.getId()), e);

            return EmailSenderResponse.builder()
                    .id(request.getId())
                    .status(EmailStatus.FAILED)
                    .build();
        }
    }
}
