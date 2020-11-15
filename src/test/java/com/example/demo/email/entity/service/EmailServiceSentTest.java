package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.aggregate.event.EmailSentEvent;
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
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class EmailServiceSentTest {

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
    public void whenSentIsValidThenReturnNonNull() {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(UUID.fromString(email.getId()))
                .sentDate(LocalDateTime.now())
                .build();

        Email email = emailService.handleSent(event);

        Assertions.assertNotNull(email);
    }

    @Test
    public void whenSentHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleCreated(null));
    }

    @Test
    public void whenSentHasNullIdThenThrowException() {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleSent(event));
    }

    @Test
    public void whenSentHasInvalidIdThenThrowException() {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(UUID.randomUUID())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleSent(event));
    }

    @Test
    public void whenSentIsValidThenExpectStatusSent() {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(UUID.fromString(email.getId()))
                .build();

        Email email = emailService.handleSent(event);

        Assertions.assertEquals(EmailStatus.SENT, email.getStatus());
    }

    @Test
    public void whenSentHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        EmailSentEvent event = EmailSentEvent.builder()
                .id(UUID.fromString(email.getId()))
                .sentDate(sentDate)
                .build();

        Email email = emailService.handleSent(event);

        Assertions.assertEquals(sentDate, email.getSentDate());
    }
}
