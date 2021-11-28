package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;

public interface EmailSenderService {

    void handleEmailSend(EmailCreatedEvent event);
}
