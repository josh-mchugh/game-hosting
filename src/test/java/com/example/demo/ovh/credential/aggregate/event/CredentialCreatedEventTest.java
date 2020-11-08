package com.example.demo.ovh.credential.aggregate.event;

import com.example.demo.ovh.credential.entity.CredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CredentialCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasSshKeyIdThenReturnSshKeyId() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .sshKeyId("ssh-key-id")
                .build();

        Assertions.assertEquals("ssh-key-id", event.getSshKeyId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasPublicKeyThenReturnPublicKey() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .publicKey("public-key")
                .build();

        Assertions.assertEquals("public-key", event.getPublicKey());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .type(CredentialType.ANSIBLE)
                .build();

        Assertions.assertEquals(CredentialType.ANSIBLE, event.getType());
    }

    @Test
    public void whenEventToString() {

        CredentialCreatedEvent event = credentialCreatedEvent();

        String toString = "CredentialCreatedEvent(id=59eb4739-2c00-494d-9959-32fbffaed0d7, sshKeyId=ssh-key-id, name=name, publicKey=public-key, type=ANSIBLE)";

        Assertions.assertEquals(toString, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(UUID.fromString("59eb4739-2c00-494d-9959-32fbffaed0d7"))
                .name("name")
                .sshKeyId("ssh-key-id")
                .publicKey("public-key")
                .build();

        Assertions.assertEquals(479641988, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        CredentialCreatedEvent event1 = credentialCreatedEvent();
        CredentialCreatedEvent event2 = credentialCreatedEvent();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        CredentialCreatedEvent event = credentialCreatedEvent();

        Assertions.assertNotEquals(event, CredentialCreatedEvent.builder().build());
    }

    private CredentialCreatedEvent credentialCreatedEvent() {

        return CredentialCreatedEvent.builder()
                .id(UUID.fromString("59eb4739-2c00-494d-9959-32fbffaed0d7"))
                .name("name")
                .sshKeyId("ssh-key-id")
                .publicKey("public-key")
                .type(CredentialType.ANSIBLE)
                .build();
    }
}
