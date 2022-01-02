package com.example.demo.awx.template.aggregate.command;

import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class AwxTemplateCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    String awxCredentialId;

    @NotNull
    UUID awxInventoryId;

    @NotNull
    UUID awxPlaybookId;

    @NotNull
    Long awxId;

    @NotBlank
    String name;

    String description;

    @NotNull
    TemplateJobType type;

    @NotNull
    TemplateVerbosity verbosity;
}
