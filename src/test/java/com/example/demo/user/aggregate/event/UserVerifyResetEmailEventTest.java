package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserVerifyResetEmailEventTest {

    @Test
    public void whenEventHasEmailThenReturnEmail() {

        UserVerifyResetEmailEvent event = UserVerifyResetEmailEvent.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", event.getEmail());
    }

    @Test
    public void whenEventHasTokenThenReturnToken() {

        UserVerifyResetEmailEvent event = UserVerifyResetEmailEvent.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", event.getToken());
    }

    @Test
    public void whenEventToString() {

        UserVerifyResetEmailEvent event = event();

        String expected = "UserVerifyResetEmailEvent(email=test@test, token=token)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserVerifyResetEmailEvent event = event();

        Assertions.assertEquals(1808666130, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserVerifyResetEmailEvent event1 = event();
        UserVerifyResetEmailEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserVerifyResetEmailEvent event = event();

        Assertions.assertNotEquals(event, UserVerifyResetEmailEvent.builder().build());
    }

    private UserVerifyResetEmailEvent event() {

        return UserVerifyResetEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();
    }
}
