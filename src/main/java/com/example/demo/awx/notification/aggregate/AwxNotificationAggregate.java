package com.example.demo.awx.notification.aggregate;

import com.example.demo.awx.notification.aggregate.command.AwxNotificationCreateCommand;
import com.example.demo.awx.notification.aggregate.event.AwxNotificationCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxNotificationAggregate {

    @AggregateIdentifier
    private UUID id;
    //TODO: Change to use AwxOrganization id instead of organizationId
    private Long awxOrganizationId;
    private Long notificationId;
    private String name;
    private String description;
    private String notificationType;
    private String webhookCallBackUrl;

    @CommandHandler
    public AwxNotificationAggregate(AwxNotificationCreateCommand command) {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(command.getId())
                .organizationId(command.getOrganizationId())
                .notificationId(command.getNotificationId())
                .name(command.getName())
                .description(command.getDescription())
                .notificationType(command.getNotificationType())
                .webhookCallBackUrl(command.getWebhookCallBackUrl())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxNotificationCreatedEvent event) {

        this.id = event.getId();
        this.awxOrganizationId = event.getOrganizationId();
        this.notificationId = event.getNotificationId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.notificationType = event.getNotificationType();
        this.webhookCallBackUrl = event.getWebhookCallBackUrl();
    }
}
