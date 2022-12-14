package com.example.demo.email.aggregate.event;

import com.example.demo.email.entity.EmailTemplateType;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class EmailCreatedEvent {

    UUID id;
    EmailTemplateType template;
    String toAddress;

    @Singular("bodyContext")
    Map<String, Object> bodyContext;

    @Singular("subjectContext")
    List<Object> subjectContext;
}
