package com.example.demo.awx.template.entity.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxTemplate {

    String id;
    Long templateId;
    String name;
    String description;
    TemplateJobType jobType;
    TemplateVerbosity verbosity;
}
