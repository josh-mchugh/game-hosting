package com.example.demo.awx.template.aggregate.event;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxTemplateCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasAwxCredentialIdThenReturnAwxCredentialId() {

        UUID awxCredentialId = UUID.randomUUID();

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .awxCredentialId(awxCredentialId)
                .build();

        Assertions.assertEquals(awxCredentialId, event.getAwxCredentialId());
    }

    @Test
    public void whenEventHasAwxInventoryIdThenReturnAwxInventoryId() {

        UUID awxInventoryId = UUID.randomUUID();

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .awxInventoryId(awxInventoryId)
                .build();

        Assertions.assertEquals(awxInventoryId, event.getAwxInventoryId());
    }

    @Test
    public void whenEventHasAwxPlaybookIdThenReturnAwxPlaybookID() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .awxPlaybookId("awxPlaybookId")
                .build();

        Assertions.assertEquals("awxPlaybookId", event.getAwxPlaybookId());
    }

    @Test
    public void whenEventHasAwxIdThenReturnAwxId() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, event.getAwxId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasDescriptionThenReturnDescription() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", event.getDescription());
    }

    @Test
    public void whenEventHasTypeThenReturnType() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .type(TemplateJobType.RUN)
                .build();

        Assertions.assertEquals(TemplateJobType.RUN, event.getType());
    }

    @Test
    public void whenEventHasVerbosityThenReturnVerbosity() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertEquals(TemplateVerbosity.NORMAL, event.getVerbosity());
    }

    @Test
    public void whenEventToString() {

        AwxTemplateCreatedEvent event = event();

        String expected = "AwxTemplateCreatedEvent(id=6d5f6f9f-c313-4d08-b15a-a8d0cdc455b0, awxCredentialId=0f569804-1137-4ec9-a52d-c969f3e1fdd0, awxInventoryId=3aef623a-629a-4c0a-b7d4-bb0138208e48, awxPlaybookId=awxPlaybookId, awxId=1, name=name, description=description, type=RUN, verbosity=NORMAL)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(UUID.fromString("6d5f6f9f-c313-4d08-b15a-a8d0cdc455b0"))
                .awxCredentialId(UUID.fromString("0f569804-1137-4ec9-a52d-c969f3e1fdd0"))
                .awxInventoryId(UUID.fromString("3aef623a-629a-4c0a-b7d4-bb0138208e48"))
                .awxPlaybookId("awxPlaybookId")
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertEquals(-994415409, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        AwxTemplateCreatedEvent event1 = event();
        AwxTemplateCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        AwxTemplateCreatedEvent event = event();

        Assertions.assertNotEquals(event, AwxTemplateCreatedEvent.builder().build());
    }

    private AwxTemplateCreatedEvent event() {

        return AwxTemplateCreatedEvent.builder()
                .id(UUID.fromString("6d5f6f9f-c313-4d08-b15a-a8d0cdc455b0"))
                .awxCredentialId(UUID.fromString("0f569804-1137-4ec9-a52d-c969f3e1fdd0"))
                .awxInventoryId(UUID.fromString("3aef623a-629a-4c0a-b7d4-bb0138208e48"))
                .awxPlaybookId("awxPlaybookId")
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();
    }
}
