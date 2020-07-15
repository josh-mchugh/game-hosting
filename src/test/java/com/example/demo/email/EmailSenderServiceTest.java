package com.example.demo.email;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailSenderService;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import com.example.demo.util.MessageUtil;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.UUID;

@ActiveProfiles({"test", "test-email"})
@SpringBootTest
public class EmailSenderServiceTest {

    @Autowired
    private IEmailSenderService emailSenderService;

    @Autowired
    private MessageUtil messageUtil;

    private GreenMail smtpServer;

    @BeforeEach
    public void setup() {

        smtpServer = new GreenMail(new ServerSetup(2525, null, "smtp"));
        smtpServer.start();
    }

    @AfterEach
    public void tearDown() {

        smtpServer.stop();
    }

    @Test
    public void testWelcomeEmail() throws MessagingException, IOException, FolderException {

        // Send an email with the template
        EmailSenderRequest request = EmailSenderRequest.builder()
                .id(UUID.randomUUID().toString())
                .template(EmailTemplate.WELCOME)
                .toAddress("email-send@email-sender.com")
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        // Assert email was processed and sent
        Assertions.assertEquals(request.getId(), response.getId());
        Assertions.assertEquals(EmailStatus.SENT, response.getStatus());

        // Retrieve the email on the test stmp server
        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage lastMessage = messages[messages.length - 1];

        // Assert received messages contains the correct information
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals(request.getToAddress(), lastMessage.getAllRecipients()[0].toString());
        Assertions.assertEquals("Welcome To Game Hosting", lastMessage.getSubject());
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Welcome to Game Hosting Services! Your account is now active and we look forward to working with you!"));

        smtpServer.purgeEmailFromAllMailboxes();
    }

    @Test
    public void testUserVerificationEmail() throws MessagingException, IOException, FolderException {

        EmailSenderRequest request = EmailSenderRequest.builder()
                .id(UUID.randomUUID().toString())
                .template(EmailTemplate.USER_VERIFICATION)
                .toAddress("user-verification@email-sender.com")
                .bodyContext("verificationUrl", "http://localhost:8080/verify/asdfasdfasdf")
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        // Assert email was processed and sent
        Assertions.assertEquals(request.getId(), response.getId());
        Assertions.assertEquals(EmailStatus.SENT, response.getStatus());

        // Retrieve the email on the test stmp server
        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage lastMessage = messages[messages.length - 1];

        // Assert received messages contains the correct information
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals(request.getToAddress(), lastMessage.getAllRecipients()[0].toString());

        Assertions.assertEquals("Email Verification", lastMessage.getSubject());
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("You are nearly there!"));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("We just need to verify your email address and then you will be on your way to setting up game servers!"));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Verify This Email"));

        smtpServer.purgeEmailFromAllMailboxes();
    }

    @Test
    public void testPasswordRecoveryEmail() throws MessagingException, IOException, FolderException {

        EmailSenderRequest request = EmailSenderRequest.builder()
                .id(UUID.randomUUID().toString())
                .template(EmailTemplate.PASSWORD_RECOVERY)
                .toAddress("password-recovery@email-sender.com")
                .bodyContext("email", "password-recovery@email-sender.com")
                .bodyContext("resetPasswordUrl", "http://localhost:8080/password-reset/asdfasdfasdf")
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        // Assert email was processed and sent
        Assertions.assertEquals(request.getId(), response.getId());
        Assertions.assertEquals(EmailStatus.SENT, response.getStatus());

        // Retrieve the email on the test stmp server
        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage lastMessage = messages[messages.length - 1];

        // Assert received messages contains the correct information
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals(request.getToAddress(), lastMessage.getAllRecipients()[0].toString());

        Assertions.assertEquals("Password Reset Email", lastMessage.getSubject());
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Somebody (hopefully you) requested a new password for the account password-recovery@email-sender.com. No changes have been made to your account yet."));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Reset My Password"));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("This link will expire in one hour and can only be used once."));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("If you didn&#39;t request this, you can ignore this email or let us know. Your password won&#39;t change until you create a new password."));

        smtpServer.purgeEmailFromAllMailboxes();
    }

    @Test
    public void testPasswordResetEmail() throws MessagingException, IOException, FolderException {

        EmailSenderRequest request = EmailSenderRequest.builder()
                .id(UUID.randomUUID().toString())
                .template(EmailTemplate.PASSWORD_RESET)
                .toAddress("password-reset@email-sender.com")
                .bodyContext("email", "password-reset@email-sender.com")
                .bodyContext("loginUrl", "http://localhost:8080/login")
                .bodyContext("forgotPasswordUrl", "http://localhost:8080/forgot-password")
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        // Assert email was processed and sent
        Assertions.assertEquals(request.getId(), response.getId());
        Assertions.assertEquals(EmailStatus.SENT, response.getStatus());

        // Retrieve the email on the test stmp server
        MimeMessage[] messages = smtpServer.getReceivedMessages();
        MimeMessage lastMessage = messages[messages.length - 1];

        // Assert received messages contains the correct information
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals(request.getToAddress(), lastMessage.getAllRecipients()[0].toString());

        Assertions.assertEquals("Password Reset Complete", lastMessage.getSubject());
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Your password reset is complete for password-reset@email-sender.com. You may sign in into your account with your new password."));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("If you did not perform this action, you can recover access by completing the reset password form at"));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("If you run into problems, please contact support by replying to this email."));
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("Please do not reply to this email with your password. We will never ask for your password, and we strongly discourage you from sharing it with anyone."));

        smtpServer.purgeEmailFromAllMailboxes();
    }

    @Test
    public void testSendEmailFailMissingTemplate() {

        EmailSenderRequest request = EmailSenderRequest.builder().build();
        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        Assertions.assertEquals(EmailStatus.FAILED, response.getStatus());
    }

    @Test
    public void testSendEmailFailMissingSendToAddress() {

        EmailSenderRequest request = EmailSenderRequest.builder()
                .template(EmailTemplate.WELCOME)
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        Assertions.assertEquals(EmailStatus.FAILED, response.getStatus());
    }
}
