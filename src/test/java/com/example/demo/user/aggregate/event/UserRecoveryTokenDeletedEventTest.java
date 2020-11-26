package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserRecoveryTokenDeletedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenDeletedEvent event = new UserRecoveryTokenDeletedEvent(id);

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventToString() {

        UserRecoveryTokenDeletedEvent event = event();

        String expected = "UserRecoveryTokenDeletedEvent(id=917cfe07-deae-4913-b23b-523c8bcd2fff)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserRecoveryTokenDeletedEvent event = event();

        Assertions.assertEquals(1982122770, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserRecoveryTokenDeletedEvent event1 = event();
        UserRecoveryTokenDeletedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserRecoveryTokenDeletedEvent event = event();

        Assertions.assertNotEquals(event, new UserRecoveryTokenDeletedEvent(UUID.randomUUID()));
    }

    private UserRecoveryTokenDeletedEvent event() {

        return new UserRecoveryTokenDeletedEvent(UUID.fromString("917cfe07-deae-4913-b23b-523c8bcd2fff"));
    }
}
