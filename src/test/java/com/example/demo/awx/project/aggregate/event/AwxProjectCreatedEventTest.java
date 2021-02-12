package com.example.demo.awx.project.aggregate.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxProjectCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxOrganizationIdThenReturnAwxOrganizationId() {

        UUID awxOrganizationId = UUID.randomUUID();

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .awxOrganizationId(awxOrganizationId)
                .build();

        Assertions.assertEquals(awxOrganizationId, event.getAwxOrganizationId());
    }

    @Test
    public void whenEventHasAwxCredentialIdThenReturnAwxCredentialId() {

        UUID awxCredentialId = UUID.randomUUID();

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .awxCredentialId(awxCredentialId)
                .build();

        Assertions.assertEquals(awxCredentialId, event.getAwxCredentialId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventHasScmTypeThenReturnScmType() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .scmType("scmType")
                .build();

        Assertions.assertEquals("scmType", event.getScmType());
    }

    @Test
    public void whenEventHasScmUrlThenReturnScmUrl() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .scmUrl("scmUrl")
                .build();

        Assertions.assertEquals("scmUrl", event.getScmUrl());
    }

    @Test
    public void whenEventHasScmBranchThenReturnScmBranch() {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .scmBranch("scmBranch")
                .build();

        Assertions.assertEquals("scmBranch", event.getScmBranch());
    }

    @Test
    public void whenEventToString() {

        AwxProjectCreatedEvent event = event();

        String expected = "AwxProjectCreatedEvent(id=05d0f4cb-55f7-4e6f-9662-a99874a037f1, awxOrganizationId=c984578f-1b32-4725-a9ec-32529b183e25, awxCredentialId=813c883b-a360-425d-bdf7-c9b0210dd432, awxId=1, name=name, description=description, scmType=scmType, scmUrl=scmUrl, scmBranch=scmBranch)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxProjectCreatedEvent event = event();

        Assertions.assertEquals(-1566225615, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxProjectCreatedEvent event1 = event();
        AwxProjectCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxProjectCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxProjectCreatedEvent.builder().build());
    }

    private AwxProjectCreatedEvent event() {

        return AwxProjectCreatedEvent.builder()
                .id(UUID.fromString("05d0f4cb-55f7-4e6f-9662-a99874a037f1"))
                .awxOrganizationId(UUID.fromString("c984578f-1b32-4725-a9ec-32529b183e25"))
                .awxCredentialId(UUID.fromString("813c883b-a360-425d-bdf7-c9b0210dd432"))
                .awxId(1L)
                .name("name")
                .description("description")
                .scmType("scmType")
                .scmUrl("scmUrl")
                .scmBranch("scmBranch")
                .build();
    }
}
