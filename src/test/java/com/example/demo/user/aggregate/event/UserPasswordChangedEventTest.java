package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserPasswordChangedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasPasswordThenReturnPassword() {

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .password("password")
                .build();

        Assertions.assertEquals("password", event.getPassword());
    }

    @Test
    public void whenEventToString() {

        UserPasswordChangedEvent event = event();

        String expected = "UserPasswordChangedEvent(id=4a3d242d-1189-4d10-917e-13c563295f10, password=password)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserPasswordChangedEvent event = event();

        Assertions.assertEquals(1877181612, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserPasswordChangedEvent event1 = event();
        UserPasswordChangedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserPasswordChangedEvent event = event();

        Assertions.assertNotEquals(event, UserPasswordChangedEvent.builder().build());
    }

    private UserPasswordChangedEvent event() {

        return UserPasswordChangedEvent.builder()
                .id(UUID.fromString("4a3d242d-1189-4d10-917e-13c563295f10"))
                .password("password")
                .build();
    }
}
