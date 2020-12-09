package com.example.demo.awx.host.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostEnabledEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostEnabledEvent event = new AwxHostEnabledEvent(id);

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventToString() {

        AwxHostEnabledEvent event = event();

        String expected =  "AwxHostEnabledEvent(id=4d487f79-5d78-4d43-a303-02196390273c)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxHostEnabledEvent event = event();

        Assertions.assertEquals(-794618022, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxHostEnabledEvent event1 = event();
        AwxHostEnabledEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxHostEnabledEvent event = event();

        Assertions.assertNotEquals(event, new AwxHostEnabledEvent(UUID.randomUUID()));
    }

    private AwxHostEnabledEvent event() {

        return new AwxHostEnabledEvent(UUID.fromString("4d487f79-5d78-4d43-a303-02196390273c"));
    }
}
