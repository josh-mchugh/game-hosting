package com.example.demo.awx.credential.aggregate.event;

import com.example.demo.awx.credential.entity.AwxCredentialType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxCredentialCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasOrganizationIdThenReturnOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, event.getAwxOrganizationId());
    }

    @Test
    public void whenEventHasCredentialIdThenReturnCredentialId() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventHasPrivateKeyThenReturnPrivateKey() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .privateKey("privateKey")
                .build();

        Assertions.assertEquals("privateKey", event.getPrivateKey());
    }

    @Test
    public void whenEventHasPassPhraseThenReturnPassPhrase() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals("passPhrase", event.getPassphrase());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .type(AwxCredentialType.MACHINE)
                .build();

        Assertions.assertEquals(AwxCredentialType.MACHINE, event.getType());
    }

    @Test
    public void whenEventToString() {

        AwxCredentialCreatedEvent event = event();

        String expected = "AwxCredentialCreatedEvent(id=9299005e-cc23-48dc-bf5d-47a4f38f3dd4, awxOrganizationId=4d5d2786-4755-447f-9816-2f5fdb7d557a, awxId=1, name=name, description=description, privateKey=privateKey, passphrase=passPhrase, type=MACHINE)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(UUID.fromString("9299005e-cc23-48dc-bf5d-47a4f38f3dd4"))
                .awxOrganizationId(UUID.fromString("4d5d2786-4755-447f-9816-2f5fdb7d557a"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .build();

        Assertions.assertEquals(-1961888190, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxCredentialCreatedEvent event1 = event();
        AwxCredentialCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxCredentialCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxCredentialCreatedEvent.builder().build());
    }

    private AwxCredentialCreatedEvent event() {

        return AwxCredentialCreatedEvent.builder()
                .id(UUID.fromString("9299005e-cc23-48dc-bf5d-47a4f38f3dd4"))
                .awxOrganizationId(UUID.fromString("4d5d2786-4755-447f-9816-2f5fdb7d557a"))
                .awxId(1L)
                .name("name")
                .description("description")
                .privateKey("privateKey")
                .passphrase("passPhrase")
                .type(AwxCredentialType.MACHINE)
                .build();
    }
}
