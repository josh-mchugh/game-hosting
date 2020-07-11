package com.example.demo.email.service.model;

import com.example.demo.email.entity.EmailTemplate;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

@Value
@Builder(builderClassName = "Builder")
public class EmailSenderRequest {

    String id;
    EmailTemplate template;
    String toAddress;
    String fromAddress;

    @Singular("context")
    Map<String, Object> context;
}
