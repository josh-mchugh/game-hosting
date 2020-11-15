package com.example.demo.email.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailSentEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailSentEvent event = EmailSentEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        EmailSentEvent event = EmailSentEvent.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, event.getSentDate());
    }

    @Test
    public void whenEventToString() {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(UUID.fromString("20c6adec-44cb-47d1-bddb-961e5fb6e120"))
                .sentDate(LocalDateTime.of(2020, 11, 11, 21, 29))
                .build();

        String toString = "EmailSentEvent(id=20c6adec-44cb-47d1-bddb-961e5fb6e120, sentDate=2020-11-11T21:29)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        EmailSentEvent event = event();

        Assertions.assertEquals(390707686, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        EmailSentEvent event1 = event();
        EmailSentEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        EmailSentEvent event = event();

        Assertions.assertNotEquals(event, EmailSentEvent.builder().build());
    }

    private EmailSentEvent event() {

        return EmailSentEvent.builder()
                .id(UUID.fromString("20c6adec-44cb-47d1-bddb-961e5fb6e120"))
                .sentDate(LocalDateTime.of(2020, 11, 11, 21, 29))
                .build();
    }
}
