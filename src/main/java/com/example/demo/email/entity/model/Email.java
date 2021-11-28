package com.example.demo.email.entity.model;

import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplateType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Email {

    UUID id;
    LocalDateTime createdDate;
    EmailTemplateType template;
    EmailStatus status;
    String toAddress;
    Map<String, Object> bodyContext;
    List<Object> subjectContext;
    LocalDateTime sentDate;
}
