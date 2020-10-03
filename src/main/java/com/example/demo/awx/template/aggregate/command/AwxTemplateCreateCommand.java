package com.example.demo.awx.template.aggregate.command;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxTemplateCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotBlank
    private final String awxCredentialId;

    @NotBlank
    private final String awxInventoryId;

    @NotBlank
    private final String awxPlaybookId;

    @NotNull
    private final Long templateId;

    @NotBlank
    private final String name;

    private final String description;

    @NotNull
    private final TemplateJobType jobType;

    @NotNull
    private final TemplateVerbosity verbosity;
}
