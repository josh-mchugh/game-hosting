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
public class EmailSenderServiceUserVerificationEmailTest {

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
                .template(EmailTemplate.USER_VERIFICATION)
                .toAddress("user-verification@email-sender.com")
                .bodyContext("verificationUrl", "http://localhost:8080/verify/asdfasdfasdf")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        Assertions.assertEquals("Email Verification", message.getSubject());
    }

    @Test
    public void whenEmailIsValidThenExpectBody() throws IOException, MessagingException {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplate.USER_VERIFICATION)
                .toAddress("user-verification@email-sender.com")
                .bodyContext("verificationUrl", "http://localhost:8080/verify/asdfasdfasdf")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        String expected = "You are nearly there! We just need to verify your email address and then you will be on your way to setting up game servers! Verify This Email Copyable link: http://localhost:8080/verify/asdfasdfasdf";

        Assertions.assertEquals(expected, Jsoup.parse(String.valueOf(message.getContent())).body().text());
    }
}
