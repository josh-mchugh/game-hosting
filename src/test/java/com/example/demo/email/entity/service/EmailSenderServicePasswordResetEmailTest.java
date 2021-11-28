package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.entity.EmailTemplateType;
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
public class EmailSenderServicePasswordResetEmailTest {

    @Autowired
    private EmailSenderService emailSenderService;

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
                .template(EmailTemplateType.PASSWORD_RESET)
                .toAddress("password-reset@email-sender.com")
                .bodyContext("email", "password-reset@email-sender.com")
                .bodyContext("loginUrl", "http://localhost:8080/login")
                .bodyContext("forgotPasswordUrl", "http://localhost:8080/forgot-password")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        Assertions.assertEquals("Password Reset Complete", message.getSubject());
    }

    @Test
    public void whenEmailIsValidThenExpectBody() throws IOException, MessagingException {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(UUID.randomUUID())
                .template(EmailTemplateType.PASSWORD_RESET)
                .toAddress("password-reset@email-sender.com")
                .bodyContext("email", "password-reset@email-sender.com")
                .bodyContext("loginUrl", "http://localhost:8080/login")
                .bodyContext("forgotPasswordUrl", "http://localhost:8080/forgot-password")
                .build();

        emailSenderService.handleEmailSend(event);

        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage message = messages[messages.length - 1];

        String expected = "Your password reset is complete for password-reset@email-sender.com. You may sign in into your account with your new password. Sign In Copyable link: http://localhost:8080/login If you did not perform this action, you can recover access by completing the reset password form at http://localhost:8080/forgot-password If you run into problems, please contact support by replying to this email. Please do not reply to this email with your password. We will never ask for your password, and we strongly discourage you from sharing it with anyone.";

        Assertions.assertEquals(expected, Jsoup.parse(String.valueOf(message.getContent())).body().text());
    }


}
