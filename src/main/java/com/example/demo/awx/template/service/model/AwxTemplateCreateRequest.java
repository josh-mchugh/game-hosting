package com.example.demo.awx.template.service.model;

import com.example.demo.awx.playbook.entity.PlaybookType;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxTemplateCreateRequest {

    Long inventoryId;
    Long credentialId;
    PlaybookType playbookType;
    Long templateId;
    String name;
    String description;
    TemplateJobType jobType;
    TemplateVerbosity verbosity;
}
