package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.command.EmailFailCommand;
import com.example.demo.email.aggregate.command.EmailSentCommand;
import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailTemplateType;
import com.example.demo.email.templates.EmailTemplate;
import com.example.demo.util.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final MessageService messageService;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;
    private final Map<EmailTemplateType, EmailTemplate> emailTemplates;
    private final CommandGateway commandGateway;

    @Override
    @DisallowReplay
    @EventHandler
    public void handleEmailSend(EmailCreatedEvent event) {

        try {

            EmailTemplate emailTemplate = emailTemplates.get(event.getTemplate());

            String subject = messageService.getMessage(emailTemplate.subject(), event.getSubjectContext());

            Context context = new Context(Locale.getDefault(), event.getBodyContext());
            String body = templateEngine.process(emailTemplate.path(), context);

            MimeMessageHelper helper = new MimeMessageHelper(emailSender.createMimeMessage(), StandardCharsets.UTF_8.name());
            helper.setTo(event.getToAddress());
            helper.setFrom(emailTemplate.fromAddress());
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(helper.getMimeMessage());

            EmailSentCommand command = EmailSentCommand.builder()
                    .id(event.getId())
                    .sentDate(LocalDateTime.now())
                    .build();

            commandGateway.send(command);

        } catch (Exception e) {

            log.error(String.format("Unable to send email for id: %s", event.getId()), e);

            commandGateway.send(new EmailFailCommand(event.getId()));
        }
    }
}
