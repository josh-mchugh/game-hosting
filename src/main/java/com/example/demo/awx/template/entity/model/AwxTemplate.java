package com.example.demo.awx.template.entity.model;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class AwxTemplate {

    String id;
    Long awxId;
    String name;
    String description;
    TemplateJobType type;
    TemplateVerbosity verbosity;
}
