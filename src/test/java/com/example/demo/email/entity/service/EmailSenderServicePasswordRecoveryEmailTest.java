package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailTemplate;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles({"test", "test-email"})
public class EmailSenderServicePasswordRecoveryEmailTest {

    @Autowired
    private IEmailSenderService emailSenderService;

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
    public void whenEmailIsValidThenExpectSubject() throws MessagingException {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.PASSWORD_RECOVERY)
                .toAddress("password-recovery@email-sender.com")
                .bodyContext("email", "password-recovery@email-sender.com")
                .bodyContext("resetPasswordUrl", "http://localhost:8080/password-reset/asdfasdfasdf")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        Assertions.assertEquals("Password Reset Email", message.getSubject());
    }

    @Test
    public void whenEmailIsValidThenExpectBody() throws IOException, MessagingException {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.PASSWORD_RECOVERY)
                .toAddress("password-recovery@email-sender.com")
                .bodyContext("email", "password-recovery@email-sender.com")
                .bodyContext("resetPasswordUrl", "http://localhost:8080/password-reset/asdfasdfasdf")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        String expected = "Somebody (hopefully you) requested a new password for the account password-recovery@email-sender.com. No changes have been made to your account yet. Reset My Password Copyable link: http://localhost:8080/password-reset/asdfasdfasdf This link will expire in one hour and can only be used once. If you didn't request this, you can ignore this email or let us know. Your password won't change until you create a new password.";

        Assertions.assertEquals(expected, Jsoup.parse(String.valueOf(message.getContent())).body().text());
    }
}
