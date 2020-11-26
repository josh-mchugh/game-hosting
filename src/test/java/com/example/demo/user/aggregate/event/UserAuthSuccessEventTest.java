package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserAuthSuccessEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasLastLongDateThenReturnLastLoginDate() {

        LocalDateTime lastLoginDate = LocalDateTime.now();

        UserAuthSuccessEvent event = UserAuthSuccessEvent.builder()
                .lastLoginDate(lastLoginDate)
                .build();

        Assertions.assertEquals(lastLoginDate, event.getLastLoginDate());
    }

    @Test
    public void whenEventToString() {

        UserAuthSuccessEvent event = event();

        String expected = "UserAuthSuccessEvent(id=7d4e1b1f-3740-4890-858d-367d9b0277e5, lastLoginDate=2020-11-21T12:30)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserAuthSuccessEvent event = event();

        Assertions.assertEquals(-621014242, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserAuthSuccessEvent event1 = event();
        UserAuthSuccessEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserAuthSuccessEvent event = event();

        Assertions.assertNotEquals(event, UserAuthSuccessEvent.builder().build());
    }

    private UserAuthSuccessEvent event() {

        return UserAuthSuccessEvent.builder()
                .id(UUID.fromString("7d4e1b1f-3740-4890-858d-367d9b0277e5"))
                .lastLoginDate(LocalDateTime.of(2020, 11, 21, 12, 30))
                .build();
    }
}
