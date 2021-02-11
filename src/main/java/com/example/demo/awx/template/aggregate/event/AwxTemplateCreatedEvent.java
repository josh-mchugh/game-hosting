package com.example.demo.awx.template.aggregate.event;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxTemplateCreatedEvent {

    UUID id;
    UUID awxCredentialId;
    String awxInventoryId;
    String awxPlaybookId;
    Long awxId;
    String name;
    String description;
    TemplateJobType type;
    TemplateVerbosity verbosity;
}
