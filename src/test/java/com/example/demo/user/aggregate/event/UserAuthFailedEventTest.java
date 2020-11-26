package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserAuthFailedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserAuthFailedEvent event = new UserAuthFailedEvent(id);

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventToString() {

        UserAuthFailedEvent event = event();

        String expected = "UserAuthFailedEvent(id=057f596d-f3e2-44d1-99af-e56fe65dfed9)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserAuthFailedEvent event = event();

        Assertions.assertEquals(-1989212603, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserAuthFailedEvent event1 = event();
        UserAuthFailedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserAuthFailedEvent event = event();

        Assertions.assertNotEquals(event, new UserAuthFailedEvent(UUID.randomUUID()));
    }

    private UserAuthFailedEvent event() {

        return new UserAuthFailedEvent(UUID.fromString("057f596d-f3e2-44d1-99af-e56fe65dfed9"));
    }
}
