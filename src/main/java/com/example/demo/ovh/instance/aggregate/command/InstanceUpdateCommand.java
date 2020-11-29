package com.example.demo.ovh.instance.aggregate.command;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class InstanceUpdateCommand {

    @NotNull
    @TargetAggregateIdentifier
    UUID id;
    String name;
    InstanceStatus status;
    String ip4Address;
    String ip6Address;
    LocalDateTime instanceCreatedDate;
}
