package com.example.demo.email.aggregate.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailSentCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        EmailSentCommand command = EmailSentCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasSentDateThenReturnSentDate() {

        LocalDateTime sentDate = LocalDateTime.now();

        EmailSentCommand command = EmailSentCommand.builder()
                .sentDate(sentDate)
                .build();

        Assertions.assertEquals(sentDate, command.getSentDate());
    }

    @Test
    public void whenCommandToString() {

        EmailSentCommand command = command();

        String toString = "EmailSentCommand(id=ea50d797-4538-4ff7-9115-fdb99dc788d3, sentDate=2020-11-11T20:54)";

        Assertions.assertEquals(toString, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        EmailSentCommand command = command();

        Assertions.assertEquals(-379902580, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        EmailSentCommand command1 = command();
        EmailSentCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        EmailSentCommand command = command();

        Assertions.assertNotEquals(command, EmailSentCommand.builder().build());
    }

    private EmailSentCommand command() {

        return EmailSentCommand.builder()
                .id(UUID.fromString("ea50d797-4538-4ff7-9115-fdb99dc788d3"))
                .sentDate(LocalDateTime.of(2020, 11, 11, 20, 54))
                .build();
    }
}
