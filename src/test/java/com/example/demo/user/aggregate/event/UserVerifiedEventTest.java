package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserVerifiedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasVerifiedDateThenReturnVerifiedDate() {

        LocalDateTime verifiedDate = LocalDateTime.now();

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .verifiedDate(verifiedDate)
                .build();

        Assertions.assertEquals(verifiedDate, event.getVerifiedDate());
    }

    @Test
    public void whenEventToString() {

        UserVerifiedEvent event =  event();

        String expected = "UserVerifiedEvent(id=6a16dd9f-5437-4d4a-aa67-20e0ecdad7c4, verifiedDate=2020-11-21T15:53)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserVerifiedEvent event = event();

        Assertions.assertEquals(230002936, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserVerifiedEvent event1 = event();
        UserVerifiedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserVerifiedEvent event = event();

        Assertions.assertNotEquals(event, UserVerifiedEvent.builder().build());
    }

    private UserVerifiedEvent event() {

        return UserVerifiedEvent.builder()
                .id(UUID.fromString("6a16dd9f-5437-4d4a-aa67-20e0ecdad7c4"))
                .verifiedDate(LocalDateTime.of(2020, 11, 21, 15, 53))
                .build();
    }
}
