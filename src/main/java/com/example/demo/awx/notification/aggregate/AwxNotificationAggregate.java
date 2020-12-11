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
    private String awxOrganizationId;
    private Long awxId;
    private String name;
    private String description;
    private String type;
    private String webhookCallBackUrl;

    @CommandHandler
    public AwxNotificationAggregate(AwxNotificationCreateCommand command) {

        AwxNotificationCreatedEvent event = AwxNotificationCreatedEvent.builder()
                .id(command.getId())
                .awxOrganizationId(command.getAwxOrganizationId())
                .awxId(command.getAwxId())
                .name(command.getName())
                .description(command.getDescription())
                .type(command.getType())
                .webhookCallBackUrl(command.getWebhookCallBackUrl())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxNotificationCreatedEvent event) {

        this.id = event.getId();
        this.awxOrganizationId = event.getAwxOrganizationId();
        this.awxId = event.getAwxId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.type = event.getType();
        this.webhookCallBackUrl = event.getWebhookCallBackUrl();
    }
}
