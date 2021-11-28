package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.aggregate.event.EmailFailedEvent;
import com.example.demo.email.aggregate.event.EmailSentEvent;
import com.example.demo.email.entity.model.Email;

public interface EmailService {

    Email handleCreated(EmailCreatedEvent event);

    Email handleSent(EmailSentEvent event);

    Email handleFailed(EmailFailedEvent event);
}
