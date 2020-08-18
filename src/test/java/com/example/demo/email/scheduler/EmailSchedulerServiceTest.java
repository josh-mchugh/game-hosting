package com.example.demo.email.scheduler;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.model.Email;
import com.example.demo.email.scheduler.service.IEmailSchedulerService;
import com.example.demo.email.service.IEmailSenderService;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.email.service.model.EmailSenderResponse;
import com.example.demo.sample.util.TestEmailCreateRequest;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles({"test"})
public class EmailSchedulerServiceTest {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IEmailSchedulerService emailSchedulerService;

    @MockBean
    private IEmailSenderService emailSenderService;

    @Test
    public void whenSchedulerProcessesZeroMailsThenReturnEmptyArray() {

        ImmutableList<Email> emails = emailSchedulerService.processEmails();

        Assertions.assertEquals(0, emails.size());
    }

    @Test
    public void whenSchedulerProcessesMailThenReturnList() {

        EmailCreateRequest createRequest = TestEmailCreateRequest.createDefault();
        Email email = emailService.handleCreateEmail(createRequest);

        EmailSenderResponse emailSenderResponse = EmailSenderResponse.builder()
                .id(email.getId())
                .status(EmailStatus.SENT)
                .build();
        Mockito.when(emailSenderService.handleEmailSend(Mockito.any())).thenReturn(emailSenderResponse);

        ImmutableList<Email> emails = emailSchedulerService.processEmails();

        Assertions.assertEquals(1, emails.size());
    }
}
