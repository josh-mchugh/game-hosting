package com.example.demo.email.entity.model;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmailTest {

    @Test
    public void whenEmailHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        Email email = Email.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, email.getId());
    }

    @Test
    public void whenEmailHasCreatedDateThenReturnCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        Email email = Email.builder()
                .createdDate(createdDate)
                .build();

        Assertions.assertEquals(createdDate, email.getCreatedDate());
    }

    @Test
    public void whenEmailHasTemplateThenReturnTemplate() {

        Email email = Email.builder()
                .template(EmailTemplate.WELCOME)
                .build();

        Assertions.assertEquals(EmailTemplate.WELCOME, email.getTemplate());
    }

    @Test
    public void whenEmailHasStatusThenReturnStatus() {

        Email email = Email.builder()
                .status(EmailStatus.QUEUED)
                .build();

        Assertions.assertEquals(EmailStatus.QUEUED, email.getStatus());
    }

    @Test
    public void whenEmailHasToAddressThenReturnToAddress() {

        Email email = Email.builder()
                .toAddress("toAddress")
                .build();

        Assertions.assertEquals("toAddress", email.getToAddress());
    }

    @Test
    public void whenEmailHasBodyContextThenReturnBodyContext() {

        Map<String, Object> map = new HashMap<>();
        map.put("test", "test");

        Email email = Email.builder()
                .bodyContext(map)
                .build();

        Map<String, Object> expected = new HashMap<>();
        expected.put("test", "test");

        Assertions.assertEquals(expected, email.getBodyContext());
    }

    @Test
    public void whenEmailHasSubjectContextThenReturnSubjectContext() {

        Email email = Email.builder()
                .subjectContext(Arrays.asList("test1", "test2"))
                .build();

        Assertions.assertEquals(Arrays.asList("test1", "test2"), email.getSubjectContext());
    }

    @Test
    public void whenEmailHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        Email email = Email.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, email.getSentDate());
    }

    @Test
    public void whenEmailToString() {

        Email email = email();

        String toString = "Email(id=24af9f86-21a8-45e2-b72c-d2cb8ffbd7c9, createdDate=2020-11-12T12:46, template=WELCOME, status=SENT, toAddress=toAddress, bodyContext={test2=test2, test1=test1}, subjectContext=[test1, test2], sentDate=2020-11-12T12:47)";

        Assertions.assertEquals(toString, email.toString());
    }

    @Test
    public void whenEmailHashCode() {

        Map<String, Object> bodyContext = new HashMap<>();
        bodyContext.put("test1", "test1");
        bodyContext.put("test2", "test2");

        Email email = Email.builder()
                .id(UUID.fromString("24af9f86-21a8-45e2-b72c-d2cb8ffbd7c9"))
                .createdDate(LocalDateTime.of(2020, 11, 12, 12, 46))
                .toAddress("toAddress")
                .bodyContext(bodyContext)
                .subjectContext(Arrays.asList("test1", "test2"))
                .sentDate(LocalDateTime.of(2020, 11, 12, 12, 47))
                .build();

        Assertions.assertEquals(520633822, email.hashCode());
    }

    @Test
    public void whenEmailEquals() {

        Email email1 = email();
        Email email2 = email();

        Assertions.assertEquals(email1, email2);
    }

    @Test
    public void whenEmailNotEquals() {

        Email email = email();

        Assertions.assertNotEquals(email, Email.builder().build());
    }

    private Email email() {

        Map<String, Object> bodyContext = new HashMap<>();
        bodyContext.put("test1", "test1");
        bodyContext.put("test2", "test2");

        return Email.builder()
                .id(UUID.fromString("24af9f86-21a8-45e2-b72c-d2cb8ffbd7c9"))
                .createdDate(LocalDateTime.of(2020, 11, 12, 12, 46))
                .template(EmailTemplate.WELCOME)
                .status(EmailStatus.SENT)
                .toAddress("toAddress")
                .bodyContext(bodyContext)
                .subjectContext(Arrays.asList("test1", "test2"))
                .sentDate(LocalDateTime.of(2020, 11, 12, 12, 47))
                .build();
    }
}
