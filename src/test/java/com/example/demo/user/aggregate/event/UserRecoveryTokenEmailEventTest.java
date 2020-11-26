package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRecoveryTokenEmailEventTest {

    @Test
    public void whenEventHasEmailThenReturnEmail() {

        UserRecoveryTokenEmailEvent event = UserRecoveryTokenEmailEvent.builder()
                .email("test@test")
                .build();

        Assertions.assertEquals("test@test", event.getEmail());
    }

    @Test
    public void whenEventHasTokenThenReturnToken() {

        UserRecoveryTokenEmailEvent event = UserRecoveryTokenEmailEvent.builder()
                .token("token")
                .build();

        Assertions.assertEquals("token", event.getToken());
    }

    @Test
    public void whenEventToString() {

        UserRecoveryTokenEmailEvent event = event();

        String expected = "UserRecoveryTokenEmailEvent(email=test@test, token=token)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserRecoveryTokenEmailEvent event = event();

        Assertions.assertEquals(1808666130, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserRecoveryTokenEmailEvent event1 = event();
        UserRecoveryTokenEmailEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserRecoveryTokenEmailEvent event = event();

        Assertions.assertNotEquals(event, UserRecoveryTokenEmailEvent.builder().build());
    }

    private UserRecoveryTokenEmailEvent event() {

        return UserRecoveryTokenEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();
    }
}
