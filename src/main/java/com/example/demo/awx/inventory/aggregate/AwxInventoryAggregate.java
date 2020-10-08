package com.example.demo.awx.inventory.aggregate;

import com.example.demo.awx.inventory.aggregate.command.AwxInventoryCreateCommand;
import com.example.demo.awx.inventory.aggregate.event.AwxInventoryCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxInventoryAggregate {

    @AggregateIdentifier
    private UUID id;
    private Long awxOrganizationId;
    private Long inventoryId;
    private String name;
    private String description;

    @CommandHandler
    public AwxInventoryAggregate(AwxInventoryCreateCommand command) {

        AwxInventoryCreatedEvent event = AwxInventoryCreatedEvent.builder()
                .id(command.getId())
                .organizationId(command.getOrganizationId())
                .inventoryId(command.getInventoryId())
                .name(command.getName())
                .description(command.getDescription())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxInventoryCreatedEvent event) {

        this.id = event.getId();
        this.awxOrganizationId = event.getOrganizationId();
        this.inventoryId = event.getInventoryId();
        this.name = event.getName();
        this.description =event.getDescription();
    }
}
