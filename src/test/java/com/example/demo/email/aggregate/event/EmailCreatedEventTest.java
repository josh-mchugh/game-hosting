package com.example.demo.email.aggregate.event;

import com.example.demo.email.entity.EmailTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmailCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasTemplateThenReturnTemplate() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .template(EmailTemplate.WELCOME)
                .build();

        Assertions.assertEquals(EmailTemplate.WELCOME, event.getTemplate());
    }

    @Test
    public void whenEventHasToAddressThenReturnToAddress() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .toAddress("toAddress")
                .build();

        Assertions.assertEquals("toAddress", event.getToAddress());
    }

    @Test
    public void whenEventHasBodyContextThenReturnBodyContext() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .bodyContext("body", "context")
                .build();

        Map<String, Object> expected = new HashMap<>();
        expected.put("body", "context");

        Assertions.assertEquals(expected, event.getBodyContext());
    }

    @Test
    public void whenEventHasBodyContextsThenReturnBodyContexts() {

        Map<String, Object> map = new HashMap<>();
        map.put("test1", "test1");

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .bodyContext(map)
                .build();

        Map<String, Object> expected = new HashMap<>();
        expected.put("test1", "test1");

        Assertions.assertEquals(expected, event.getBodyContext());
    }

    @Test
    public void whenEventClearsBodyContextsThenExpectEmptyMap() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .bodyContext("body", "context")
                .clearBodyContext()
                .build();

        Assertions.assertEquals(new HashMap<>(), event.getBodyContext());
    }

    @Test
    public void whenEventSubjectContextThenExpectSubjectContext() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .subjectContext("test")
                .build();

        Assertions.assertEquals(Collections.singletonList("test"), event.getSubjectContext());
    }

    @Test
    public void whenEventSubjectContextsThenExpectSubjectContexts() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .subjectContext(Arrays.asList("test1", "test2"))
                .build();

        Assertions.assertEquals(Arrays.asList("test1", "test2"), event.getSubjectContext());
    }

    @Test
    public void whenEventClearSubjectContextThenExpectEmptyList() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .subjectContext("test")
                .clearSubjectContext()
                .build();

        Assertions.assertEquals(new ArrayList<>(), event.getSubjectContext());
    }

    @Test
    public void whenEventToString() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.fromString("265faca8-3a36-4bf3-af37-182be79b92b8"))
                .template(EmailTemplate.WELCOME)
                .toAddress("toAddress")
                .bodyContext("test1", "test1")
                .bodyContext("test2", "test2")
                .subjectContext("test1")
                .subjectContext("test2")
                .build();

        String toString = "EmailCreatedEvent(id=265faca8-3a36-4bf3-af37-182be79b92b8, template=WELCOME, toAddress=toAddress, bodyContext={test1=test1, test2=test2}, subjectContext=[test1, test2])";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        EmailCreatedEvent event = event();

        Assertions.assertEquals(-1176646393, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        EmailCreatedEvent event1 = event();
        EmailCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        EmailCreatedEvent event = event();

        Assertions.assertNotEquals(event, EmailCreatedEvent.builder().build());
    }

    private EmailCreatedEvent event() {

        return EmailCreatedEvent.builder()
                .id(UUID.fromString("265faca8-3a36-4bf3-af37-182be79b92b8"))
                .toAddress("toAddress")
                .bodyContext("test1", "test1")
                .bodyContext("test2", "test2")
                .subjectContext("test1")
                .subjectContext("test2")
                .build();
    }
}
