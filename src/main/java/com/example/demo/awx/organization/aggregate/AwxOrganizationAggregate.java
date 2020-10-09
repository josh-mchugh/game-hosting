package com.example.demo.awx.organization.aggregate;

import com.example.demo.awx.organization.aggregate.command.AwxOrganizationCreateCommand;
import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxOrganizationAggregate {

    @AggregateIdentifier
    private UUID id;
    private Long organizationId;
    private String name;
    private String description;

    @CommandHandler
    public AwxOrganizationAggregate(AwxOrganizationCreateCommand command) {

        AwxOrganizationCreatedEvent event = AwxOrganizationCreatedEvent.builder()
                .id(command.getId())
                .organizationId(command.getOrganizationId())
                .name(command.getName())
                .description(command.getDescription())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxOrganizationCreatedEvent event) {

        this.id = event.getId();
        this.organizationId = event.getOrganizationId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
