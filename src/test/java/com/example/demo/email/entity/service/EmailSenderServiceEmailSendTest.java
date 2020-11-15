package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.command.EmailFailCommand;
import com.example.demo.email.aggregate.command.EmailSentCommand;
import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailTemplate;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles({"test", "test-email"})
public class EmailSenderServiceEmailSendTest {

    @Autowired
    private IEmailSenderService emailSenderService;

    @MockBean
    private CommandGateway commandGateway;

    private GreenMail smtpServer;

    @BeforeEach
    public void setup() {

        smtpServer = new GreenMail(new ServerSetup(2525, null, "smtp"));
        smtpServer.start();
    }

    @AfterEach
    public void tearDown() throws FolderException {

        smtpServer.purgeEmailFromAllMailboxes();
        smtpServer.stop();
    }

    @Test
    public void whenEmailIsValidThenExpectCommandGatewayCalled() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("email-send@email-sender.com")
                .build();

        emailSenderService.handleEmailSend(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailSentCommand.class));
    }

    @Test
    public void whenEmailIsValidThenExpectOneMessage() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("email-send@email-sender.com")
                .build();

        emailSenderService.handleEmailSend(event);

        Assertions.assertEquals(1, smtpServer.getReceivedMessages().length);
    }

    @Test
    public void whenEmailIsValidThenExpectSender() throws MessagingException {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .toAddress("email-send@email-sender.com")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        Assertions.assertEquals("email-send@email-sender.com", message.getAllRecipients()[0].toString());
    }

    @Test
    public void whenEmailHasErrorThenExpectExpectZeroMessages() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        emailSenderService.handleEmailSend(event);

        Assertions.assertEquals(0, smtpServer.getReceivedMessages().length);
    }

    @Test
    public void whenEmailHasNullTemplateThenExpectCommandGatewayCalled() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .build();

        emailSenderService.handleEmailSend(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailFailCommand.class));
    }

    @Test
    public void whenEmailHasNullToAddressThenExpectCommandGatewayCalled() {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.WELCOME)
                .build();

        emailSenderService.handleEmailSend(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailFailCommand.class));
    }
}
