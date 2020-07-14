package com.example.demo.email;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailSenderService;
import com.example.demo.email.service.model.EmailSenderRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
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
    public void testEmailSend() throws MessagingException, IOException {

        // Send an email with the template
        EmailSenderRequest request = EmailSenderRequest.builder()
                .id(UUID.randomUUID().toString())
                .template(EmailTemplate.TEST)
                .toAddress("email-send@email-sender.com")
                .context("name", "tester")
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
        Assertions.assertEquals(EmailTemplate.TEST.getSubject(), lastMessage.getSubject());
        Assertions.assertTrue(String.valueOf(lastMessage.getContent()).contains("tester"));
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
                .template(EmailTemplate.TEST)
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        Assertions.assertEquals(EmailStatus.FAILED, response.getStatus());
    }
}
