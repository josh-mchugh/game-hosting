package com.example.demo.email.service.model;

import com.example.demo.email.entity.EmailTemplate;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder(builderClassName = "Builder")
public class EmailSenderRequest {

    String id;
    EmailTemplate template;
    String toAddress;

    @Singular("bodyContext")
    Map<String, Object> bodyContext;

    @Singular("subjectContext")
    List<Object> subjectContext;
}
