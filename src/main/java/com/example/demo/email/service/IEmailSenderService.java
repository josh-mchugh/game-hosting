package com.example.demo.email.service;

import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;

public interface IEmailSenderService {

    EmailSenderResponse handleEmailSend(EmailSenderRequest request);
}
