package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.aggregate.event.EmailFailedEvent;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.entity.model.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class EmailServiceFailedTest {

    @Autowired
    private IEmailService emailService;

    private Email email;

    @BeforeEach
    public void setup() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        email = emailService.handleCreated(event);
    }

    @Test
    public void whenFailedIsValidThenReturnNotNull() {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(email.getId())
                .build();

        Email email = emailService.handleFailed(event);

        Assertions.assertNotNull(email);
    }

    @Test
    public void whenFailedParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleFailed(null));
    }

    @Test
    public void whenFailedHasNullIdThenThrowException() {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleFailed(event));
    }

    @Test
    public void whenFailedHasInvalidIdThenThrowException() {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleFailed(event));
    }

    @Test
    public void whenFailedIsValidThenExpectStatusFailed() {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(email.getId())
                .build();

        Email email = emailService.handleFailed(event);

        Assertions.assertEquals(EmailStatus.FAILED, email.getStatus());
    }
}
