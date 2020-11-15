package com.example.demo.email.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class EmailFailedEventTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenCommandToString() {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(UUID.fromString("000646d1-0155-4b82-b791-3b50cf101bfd"))
                .build();

        String toString = "EmailFailedEvent(id=000646d1-0155-4b82-b791-3b50cf101bfd)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenCommandHashCode() {

        EmailFailedEvent event = event();

        Assertions.assertEquals(2043817529, event.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        EmailFailedEvent event1 = event();
        EmailFailedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenCommandNotEquals() {

        EmailFailedEvent event = event();

        Assertions.assertNotEquals(event, EmailFailedEvent.builder().build());
    }

    private EmailFailedEvent event() {

        return EmailFailedEvent.builder()
                .id(UUID.fromString("000646d1-0155-4b82-b791-3b50cf101bfd"))
                .build();
    }
}
