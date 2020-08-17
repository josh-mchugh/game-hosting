package com.example.demo.email.scheduler.service;

import com.example.demo.email.model.Email;
import com.google.common.collect.ImmutableList;

public interface IEmailSchedulerService {

    ImmutableList<Email> processEmails();
}
