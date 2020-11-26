package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserVerifyResetEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasTokenThenReturnToken() {

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", event.getToken());
    }

    @Test
    public void whenEventHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        UserVerifyResetEvent event = UserVerifyResetEvent.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, event.getSentDate());
    }

    @Test
    public void whenEventToString() {

        UserVerifyResetEvent event = event();

        String expected = "UserVerifyResetEvent(id=575e43da-8d8c-43bf-b7f2-b1303a6a98ec, token=token, sentDate=2020-11-21T15:38)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserVerifyResetEvent event = event();

        Assertions.assertEquals(696825137, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserVerifyResetEvent event1 = event();
        UserVerifyResetEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserVerifyResetEvent event = event();

        Assertions.assertNotEquals(event, UserVerifyResetEvent.builder().build());
    }

    private UserVerifyResetEvent event() {

        return UserVerifyResetEvent.builder()
                .id(UUID.fromString("575e43da-8d8c-43bf-b7f2-b1303a6a98ec"))
                .token("token")
                .sentDate(LocalDateTime.of(2020, 11, 21, 15,38))
                .build();
    }
}
