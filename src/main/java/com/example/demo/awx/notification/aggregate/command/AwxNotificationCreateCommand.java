package com.example.demo.awx.notification.aggregate.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder(builderClassName = "Builder")
public class AwxNotificationCreateCommand {

    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;

    @NotNull
    private final Long organizationId;

    @NotNull
    private final Long notificationId;

    @NotBlank
    private final String name;

    private final String description;

    @NotBlank
    private final String notificationType;

    private final String webhookCallBackUrl;
}
