package com.example.demo.email.model;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplate;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder(builderClassName = "Builder")
public class Email {

    String id;
    LocalDateTime createdDate;
    EmailTemplate template;
    EmailStatus status;
    String toAddress;
    Map<String, Object> context;
    LocalDateTime mailingDate;
}
