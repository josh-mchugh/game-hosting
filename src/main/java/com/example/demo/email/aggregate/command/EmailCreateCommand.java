package com.example.demo.email.aggregate.command;

import com.example.demo.email.entity.EmailTemplateType;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class EmailCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotNull
    EmailTemplateType template;

    @NotBlank
    String toAddress;

    @Singular("bodyContext")
    Map<String, Object> bodyContext;

    @Singular("subjectContext")
    List<Object> subjectContext;
}
