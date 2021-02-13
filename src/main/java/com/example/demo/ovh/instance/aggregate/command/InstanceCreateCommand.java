package com.example.demo.ovh.instance.aggregate.command;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;

    @NotBlank
    String flavorId;

    @NotBlank
    String imageId;

    @NotNull
    UUID credentialId;

    @NotBlank
    String instanceGroupId;

    @NotBlank
    String ovhId;

    @NotNull
    InstanceStatus status;

    String name;
    LocalDateTime instanceCreatedDate;
}
