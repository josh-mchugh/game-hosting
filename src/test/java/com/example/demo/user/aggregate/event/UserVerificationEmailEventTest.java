package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserVerificationEmailEventTest {

    @Test
    public void whenEventHasEmailThenReturnEmail() {

        UserVerificationEmailEvent event = UserVerificationEmailEvent.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", event.getEmail());
    }

    @Test
    public void whenEventHasTokenThenReturnToken() {

        UserVerificationEmailEvent event = UserVerificationEmailEvent.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", event.getToken());
    }

    @Test
    public void whenEventToString() {

        UserVerificationEmailEvent event = event();

        String expected = "UserVerificationEmailEvent(email=test@test, token=token)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserVerificationEmailEvent event = event();

        Assertions.assertEquals(1808666130, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserVerificationEmailEvent event1 = event();
        UserVerificationEmailEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserVerificationEmailEvent event = event();

        Assertions.assertNotEquals(event, UserVerificationEmailEvent.builder().build());
    }

    private UserVerificationEmailEvent event() {

        return UserVerificationEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();
    }
}
