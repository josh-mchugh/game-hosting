package com.example.demo.email.aggregate.command;

import com.example.demo.email.entity.EmailTemplateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmailCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasTemplateThenReturnTemplate() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .template(EmailTemplateType.WELCOME)
                .build();

        Assertions.assertEquals(EmailTemplateType.WELCOME, command.getTemplate());
    }

    @Test
    public void whenCommandHasToAddressThenReturnToAddress() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .toAddress("toAddress")
                .build();

        Assertions.assertEquals("toAddress", command.getToAddress());
    }

    @Test
    public void whenCommandHasBodyContextThenReturnBodyContext() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .bodyContext("body", "context")
                .build();

        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("body", "context");

        Assertions.assertEquals(expectedMap, command.getBodyContext());
    }

    @Test
    public void whenCommandHasBodyContextsThenReturnBodyContexts() {

        Map<String, Object> map = new HashMap<>();
        map.put("body", "context");

        EmailCreateCommand command = EmailCreateCommand.builder()
                .bodyContext(map)
                .build();

        Assertions.assertEquals(map, command.getBodyContext());
    }

    @Test
    public void whenCommandClearsBodyContextThenExpectEmptyMap() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .bodyContext("body", "context")
                .clearBodyContext()
                .build();

        Assertions.assertEquals(new HashMap<>(), command.getBodyContext());
    }

    @Test
    public void whenCommandHasSubjectContextThenExpectSubjectContext() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .subjectContext("test")
                .build();

        Assertions.assertEquals(Collections.singletonList("test"), command.getSubjectContext());
    }

    @Test
    public void whenCommandHasSubjectContextsThenExpectSubjectContexts() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .subjectContext(Arrays.asList("test1", "test2"))
                .build();

        Assertions.assertEquals(Arrays.asList("test1", "test2"), command.getSubjectContext());
    }

    @Test
    public void whenCommandClearsSubjectContextThenExpectEmptyList() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .subjectContext("test")
                .clearSubjectContext()
                .build();

        Assertions.assertEquals(new ArrayList<>(), command.getSubjectContext());
    }

    @Test
    public void whenCommandToString() {

        EmailCreateCommand command = EmailCreateCommand.builder()
                .id(UUID.fromString("a6d2b836-2ee9-4b4e-9547-0b48c7244372"))
                .template(EmailTemplateType.WELCOME)
                .toAddress("toAddress")
                .bodyContext("test", "context")
                .subjectContext("test1")
                .build();

        String toString = "EmailCreateCommand(id=a6d2b836-2ee9-4b4e-9547-0b48c7244372, template=WELCOME, toAddress=toAddress, bodyContext={test=context}, subjectContext=[test1])";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        EmailCreateCommand command = command();

        Assertions.assertEquals(1576978764, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        EmailCreateCommand command1 = command();
        EmailCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        EmailCreateCommand command = command();

        Assertions.assertNotEquals(command, EmailCreateCommand.builder().build());
    }

    private EmailCreateCommand command() {

        return EmailCreateCommand.builder()
                .id(UUID.fromString("a6d2b836-2ee9-4b4e-9547-0b48c7244372"))
                .toAddress("toAddress")
                .bodyContext("test", "context")
                .subjectContext("test1")
                .build();
    }
}
