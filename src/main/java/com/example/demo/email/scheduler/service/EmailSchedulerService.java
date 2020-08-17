package com.example.demo.email.scheduler.service;

import com.example.demo.email.model.Email;
import com.example.demo.email.service.IEmailSenderService;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailProcessedRequest;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailSchedulerService implements IEmailSchedulerService {

    private final IEmailService emailService;
    private final IEmailSenderService emailSenderService;

    @Override
    public ImmutableList<Email> processEmails() {

        List<Email> processedEmails = new ArrayList<>();

        while(emailService.existsPendingEmails()) {

            List<Email> emails = emailService.getPendingEmails().stream()
                    .map(this::buildEmailSenderRequest)
                    .map(emailSenderService::handleEmailSend)
                    .map(this::buildEmailProcessRequest)
                    .map(emailService::handleProcessedEmail)
                    .collect(Collectors.toList());

            processedEmails.addAll(emails);
        }

        return ImmutableList.copyOf(processedEmails);
    }

    private EmailSenderRequest buildEmailSenderRequest(Email email) {

        return EmailSenderRequest.builder()
                .id(email.getId())
                .toAddress(email.getToAddress())
                .template(email.getTemplate())
                .bodyContext(email.getBodyContext())
                .subjectContext(email.getSubjectContext())
                .build();
    }

    private EmailProcessedRequest buildEmailProcessRequest(EmailSenderResponse response) {

        return EmailProcessedRequest.builder()
                .id(response.getId())
                .status(response.getStatus())
                .build();
    }
}
