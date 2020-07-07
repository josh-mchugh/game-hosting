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

@ActiveProfiles("test")
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
                .toAddress("tester@test.com")
                .context("name", "tester")
                .build();

        EmailSenderResponse response = emailSenderService.handleEmailSend(request);

        // Assert email was processed and sent
        Assertions.assertEquals(request.getId(), response.getId());
        Assertions.assertEquals(EmailStatus.SENT, response.getStatus());

        // Retrieve the email on the test stmp server
        MimeMessage[] messages = smtpServer.getReceivedMessages();

        // Assert received messages contains the correct information
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals(request.getToAddress(), messages[0].getAllRecipients()[0].toString());
        Assertions.assertEquals(EmailTemplate.TEST.getSubject(), messages[0].getSubject());
        Assertions.assertTrue(String.valueOf(messages[0].getContent()).contains("tester"));
    }
}
