package com.example.demo.user.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserWelcomeEmailEventTest {

    @Test
    public void whenEventHasEmailThenReturnEmail() {

        UserWelcomeEmailEvent event = new UserWelcomeEmailEvent("test@test");

        Assertions.assertEquals("test@test", event.getEmail());
    }

    @Test
    public void whenEventToString() {

        UserWelcomeEmailEvent event = event();

        String expected = "UserWelcomeEmailEvent(email=test@test)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        UserWelcomeEmailEvent event = event();

        Assertions.assertEquals(-1208751173, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        UserWelcomeEmailEvent event1 = event();
        UserWelcomeEmailEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        UserWelcomeEmailEvent event = event();

        Assertions.assertNotEquals(event, new UserWelcomeEmailEvent("email@email"));
    }

    private UserWelcomeEmailEvent event() {

        return new UserWelcomeEmailEvent("test@test");
    }
}
