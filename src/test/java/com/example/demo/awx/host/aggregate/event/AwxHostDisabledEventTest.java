package com.example.demo.awx.host.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxHostDisabledEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxHostDisabledEvent event = new AwxHostDisabledEvent(id);

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventToString() {

        AwxHostDisabledEvent event = event();

        String expected =  "AwxHostDisabledEvent(id=4d487f79-5d78-4d43-a303-02196390273c)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxHostDisabledEvent event = event();

        Assertions.assertEquals(-794618022, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxHostDisabledEvent event1 = event();
        AwxHostDisabledEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxHostDisabledEvent event = event();

        Assertions.assertNotEquals(event, new AwxHostDisabledEvent(UUID.randomUUID()));
    }

    private AwxHostDisabledEvent event() {

        return new AwxHostDisabledEvent(UUID.fromString("4d487f79-5d78-4d43-a303-02196390273c"));
    }
}
