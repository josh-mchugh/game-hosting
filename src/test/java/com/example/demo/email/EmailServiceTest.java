package com.example.demo.email;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.model.Email;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.email.service.model.EmailProcessedRequest;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private IEmailService emailService;

    @Test
    public void testCreateEmailRequest() {

        EmailCreateRequest request = createEmailRequest();
        Email email = emailService.handleCreateEmail(request);

        Assertions.assertNotNull(email.getId());
        Assertions.assertNotNull(email.getCreatedDate());
        Assertions.assertEquals(request.getTemplate(), email.getTemplate());
        Assertions.assertEquals(EmailStatus.PENDING, email.getStatus());
        Assertions.assertEquals(request.getToAddress(), email.getToAddress());
        Assertions.assertEquals(3, email.getBodyContext().size());
        Assertions.assertEquals(request.getBodyContext().get("loginUrl"), email.getBodyContext().get("loginUrl"));
        Assertions.assertEquals(request.getBodyContext().get("forgotPasswordUrl"), email.getBodyContext().get("forgotPasswordUrl"));
        Assertions.assertEquals(request.getBodyContext().get("email"), email.getBodyContext().get("email"));
        Assertions.assertEquals(2, request.getSubjectContext().size());
        Assertions.assertEquals("test1", request.getSubjectContext().get(0));
        Assertions.assertNull(email.getMailingDate());
    }

    @Test
    public void testExistingPendingEmails() {

        EmailCreateRequest request = createEmailRequest();
        emailService.handleCreateEmail(request);

        Boolean exists = emailService.existsPendingEmails();

        Assertions.assertTrue(exists);
    }

    @Test
    public void testGetPendingEmails() {

        EmailCreateRequest request = createEmailRequest();
        emailService.handleCreateEmail(request);

        ImmutableList<Email> emails = emailService.getPendingEmails();

        boolean isAllPending = emails.stream()
                .allMatch(email -> EmailStatus.PENDING.equals(email.getStatus()));

        Assertions.assertTrue(isAllPending);
        Assertions.assertTrue(emails.size() > 1);
    }

    @Test
    public void testHandleProcessedSentEmail() {

        EmailCreateRequest request = createEmailRequest();
        Email email = emailService.handleCreateEmail(request);

        EmailProcessedRequest processedRequest = EmailProcessedRequest.builder()
                .id(email.getId())
                .status(EmailStatus.SENT)
                .build();

        Email processedEmail = emailService.handleProcessedEmail(processedRequest);

        Assertions.assertEquals(email.getId(), processedEmail.getId());
        Assertions.assertEquals(EmailStatus.SENT, processedEmail.getStatus());
        Assertions.assertNotNull(processedEmail.getMailingDate());
    }

    @Test
    public void testHandleProcessedFailedEmail() {

        EmailCreateRequest request = createEmailRequest();
        Email email = emailService.handleCreateEmail(request);

        EmailProcessedRequest processedRequest = EmailProcessedRequest.builder()
                .id(email.getId())
                .status(EmailStatus.FAILED)
                .build();

        Email processedEmail = emailService.handleProcessedEmail(processedRequest);

        Assertions.assertEquals(email.getId(), processedEmail.getId());
        Assertions.assertEquals(EmailStatus.FAILED, processedEmail.getStatus());
        Assertions.assertNull(processedEmail.getMailingDate());
    }

    private EmailCreateRequest createEmailRequest() {

        return EmailCreateRequest.builder()
                .template(EmailTemplate.PASSWORD_RESET)
                .toAddress("test@test.com")
                .bodyContext("loginUrl", "http://localhost:8080/login")
                .bodyContext("forgotPasswordUrl", "http://localhost:8080/forgot-password")
                .bodyContext("email", "test@test.com")
                .subjectContext("test1")
                .subjectContext("test2")
                .build();
    }
}
