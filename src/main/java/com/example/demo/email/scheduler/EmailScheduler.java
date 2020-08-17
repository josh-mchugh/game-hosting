package com.example.demo.email.scheduler;

import com.example.demo.email.model.Email;
import com.example.demo.email.scheduler.service.IEmailSchedulerService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    @Autowired
    private IEmailSchedulerService emailSchedulerService;

    @Scheduled(fixedDelayString = "${app.email.scheduler-delay}", initialDelayString = "${app.email.scheduler-initial-delay}")
    public void scheduledEmailProcessing() {

        log.info("Processing Queued Emails");

        ImmutableList<Email> emails = emailSchedulerService.processEmails();

        log.info("Processed emails: {}", emails.size());
        log.info("Finished Processing Queued Emails");
    }
}
