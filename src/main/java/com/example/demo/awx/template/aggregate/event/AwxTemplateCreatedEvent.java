package com.example.demo.awx.template.aggregate.event;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxTemplateCreatedEvent {

    private final UUID id;
    private final String awxCredentialId;
    private final String awxInventoryId;
    private final String awxPlaybookId;
    private final Long templateId;
    private final String name;
    private final String description;
    private final TemplateJobType jobType;
    private final TemplateVerbosity verbosity;
}
