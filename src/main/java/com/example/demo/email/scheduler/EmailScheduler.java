package com.example.demo.email.scheduler;

import com.example.demo.email.model.Email;
import com.example.demo.email.service.IEmailSenderService;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailProcessedRequest;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final IEmailService emailService;
    private final IEmailSenderService emailSenderService;

    @Scheduled(fixedDelayString = "${app.email.scheduler-delay}")
    public void scheduledEmailProcessing() {

        while(emailService.existsPendingEmails()) {

            List<Email> processedMails = emailService.getPendingEmails().stream()
                    .map(this::buildEmailSenderRequest)
                    .map(emailSenderService::handleEmailSend)
                    .map(this::buildEmailProcessRequest)
                    .map(emailService::handleProcessedEmail)
                    .collect(Collectors.toList());
        }
    }

    private EmailSenderRequest buildEmailSenderRequest(Email email) {

        return EmailSenderRequest.builder()
                .id(email.getId())
                .toAddress(email.getToAddress())
                .template(email.getTemplate())
                .bodyContext(email.getContext())
                .build();
    }

    private EmailProcessedRequest buildEmailProcessRequest(EmailSenderResponse response) {

        return EmailProcessedRequest.builder()
                .id(response.getId())
                .status(response.getStatus())
                .build();
    }
}
