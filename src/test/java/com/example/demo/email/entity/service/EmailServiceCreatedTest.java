package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.entity.model.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class EmailServiceCreatedTest {

    @Autowired
    private IEmailService emailService;

    @Test
    public void whenCreatedIsValidThenReturnNotNull() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertNotNull(email);
    }

    @Test
    public void whenCreatedParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleCreated(null));
    }

    @Test
    public void whenCreatedHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(id)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(id, email.getId());
    }

    @Test
    public void whenCreatedHasNullIdThenThrowException() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(null)
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> emailService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasTemplateThenReturnTemplate() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(EmailTemplate.WELCOME, email.getTemplate());
    }

    @Test
    public void whenCreatedHasNullTemplateThenThrowException() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(null)
                .toAddress("toAddress")
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> emailService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasStatusQueuedThenReturnStatus() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(EmailStatus.QUEUED, email.getStatus());
    }

    @Test
    public void whenCreatedHasToAddressThenReturnToAddress() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals("toAddress", email.getToAddress());
    }

    @Test
    public void whenCreatedHasNullToAddressThenThrowException() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> emailService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasBodyContextThenReturnBodyContext() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .bodyContext("test", "test")
                .build();

        Email email = emailService.handleCreated(event);

        Map<String, Object> expected = new HashMap<>();
        expected.put("test", "test");

        Assertions.assertEquals(expected, email.getBodyContext());
    }

    @Test
    public void whenCreatedHasEmptyBodyContextThenReturnEmptyMap() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(new HashMap<>(), email.getBodyContext());
    }

    @Test
    public void whenCreatedHasSubjectContextThenReturnSubjectContext() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .subjectContext(Arrays.asList("test1", "test2"))
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(Arrays.asList("test1", "test2"), email.getSubjectContext());
    }

    @Test
    public void whenCreatedHasEmptySubjectContextThenExpectEmptyList() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .build();

        Email email = emailService.handleCreated(event);

        Assertions.assertEquals(new ArrayList<>(), email.getSubjectContext());
    }
}
