package com.example.demo.email.service;

import com.example.demo.email.model.Email;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.email.service.model.EmailProcessedRequest;
import com.google.common.collect.ImmutableList;

public interface IEmailService {

    Email handleCreateEmail(EmailCreateRequest request);

    Boolean existsPendingEmails();

    ImmutableList<Email> getPendingEmails();

    Email handleProcessedEmail(EmailProcessedRequest request);
}
