package com.example.demo.awx.template.aggregate.command;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AwxTemplateCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasAwxCredentialIdThenAwxCredentialId() {

        UUID awxCredentialId = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .awxCredentialId(awxCredentialId)
                .build();

        Assertions.assertEquals(awxCredentialId, command.getAwxCredentialId());
    }

    @Test
    public void whenCommandHasAwxInventoryIdThenAwxInventoryId() {

        UUID awxInventoryId = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .awxInventoryId(awxInventoryId)
                .build();

        Assertions.assertEquals(awxInventoryId, command.getAwxInventoryId());
    }

    @Test
    public void whenCommandHasAwxPlaybookIdThenReturnAwxPlaybookId() {

        UUID awxPlaybookId = UUID.randomUUID();

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .awxPlaybookId(awxPlaybookId)
                .build();

        Assertions.assertEquals(awxPlaybookId, command.getAwxPlaybookId());
    }

    @Test
    public void whenCommandHasAwxIdThenReturnAwxId() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .awxId(1L)
                .build();

        Assertions.assertEquals(1L, command.getAwxId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasDescriptionThenReturnDescription() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .description("description")
                .build();

        Assertions.assertEquals("description", command.getDescription());
    }

    @Test
    public void whenCommandHasTypeThenReturnType() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .type(TemplateJobType.RUN)
                .build();

        Assertions.assertEquals(TemplateJobType.RUN, command.getType());
    }

    @Test
    public void whenCommandHasVerbosityThenReturnVerbosity() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .verbosity(TemplateVerbosity.NORMAL)
                .build();

        Assertions.assertEquals(TemplateVerbosity.NORMAL, command.getVerbosity());
    }

    @Test
    public void whenCommandToString() {

        AwxTemplateCreateCommand command = command();

        String expected = "AwxTemplateCreateCommand(id=e0273a31-54c3-424a-b42a-cd2ab4532384, awxCredentialId=9158639a-8098-45d3-958f-b4458765b9b4, awxInventoryId=d3879efa-a6f8-405a-b1bb-6f91133e9df9, awxPlaybookId=30c78820-73e5-4c38-b30c-b58467f2fcc6, awxId=1, name=name, description=description, type=RUN, verbosity=NORMAL)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        AwxTemplateCreateCommand command = AwxTemplateCreateCommand.builder()
                .id(UUID.fromString("e0273a31-54c3-424a-b42a-cd2ab4532384"))
                .awxCredentialId(UUID.fromString("9158639a-8098-45d3-958f-b4458765b9b4"))
                .awxInventoryId(UUID.fromString("d3879efa-a6f8-405a-b1bb-6f91133e9df9"))
                .awxPlaybookId(UUID.fromString("30c78820-73e5-4c38-b30c-b58467f2fcc6"))
                .awxId(1L)
                .name("name")
                .description("description")
                .build();

        Assertions.assertEquals(-116005914, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        AwxTemplateCreateCommand command1 = command();
        AwxTemplateCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        AwxTemplateCreateCommand command = command();

        Assertions.assertNotEquals(command, AwxTemplateCreateCommand.builder().build());
    }

    private AwxTemplateCreateCommand command() {

        return AwxTemplateCreateCommand.builder()
                .id(UUID.fromString("e0273a31-54c3-424a-b42a-cd2ab4532384"))
                .awxCredentialId(UUID.fromString("9158639a-8098-45d3-958f-b4458765b9b4"))
                .awxInventoryId(UUID.fromString("d3879efa-a6f8-405a-b1bb-6f91133e9df9"))
                .awxPlaybookId(UUID.fromString("30c78820-73e5-4c38-b30c-b58467f2fcc6"))
                .awxId(1L)
                .name("name")
                .description("description")
                .type(TemplateJobType.RUN)
                .verbosity(TemplateVerbosity.NORMAL)
                .build();
    }
}
