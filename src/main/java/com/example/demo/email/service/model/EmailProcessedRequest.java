package com.example.demo.email.service.model;

import com.example.demo.email.entity.EmailStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class EmailProcessedRequest {

    String id;
    EmailStatus status;
}
