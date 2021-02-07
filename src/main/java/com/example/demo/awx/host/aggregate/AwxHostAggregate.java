package com.example.demo.awx.host.aggregate;

import com.example.demo.awx.host.aggregate.command.AwxHostCreateCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostDisableCommand;
import com.example.demo.awx.host.aggregate.command.AwxHostEnableCommand;
import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostDisabledEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostEnabledEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxHostAggregate {

    @AggregateIdentifier
    private UUID id;
    private String awxInventoryId;
    private String instanceId;
    private Long awxId;
    private String hostname;
    private String description;
    private Boolean enabled;

    @CommandHandler
    public AwxHostAggregate(AwxHostCreateCommand command) {

        AwxHostCreatedEvent event = AwxHostCreatedEvent.builder()
                .id(command.getId())
                .awxInventoryId(command.getAwxInventoryId())
                .instanceId(command.getInstanceId())
                .awxId(command.getAwxId())
                .hostname(command.getHostname())
                .description(command.getDescription())
                .enabled(command.getEnabled())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxHostCreatedEvent event) {

        this.id = event.getId();
        this.awxInventoryId = event.getAwxInventoryId();
        this.instanceId = event.getInstanceId();
        this.awxId = event.getAwxId();
        this.hostname = event.getHostname();
        this.description = event.getDescription();
        this.enabled = event.getEnabled();
    }

    @CommandHandler
    public void on(AwxHostEnableCommand command) {

        AwxHostEnabledEvent event = new AwxHostEnabledEvent(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxHostEnabledEvent event) {

        this.enabled = true;
    }

    @CommandHandler
    public void on(AwxHostDisableCommand command) {

        AwxHostDisabledEvent event = new AwxHostDisabledEvent(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxHostDisabledEvent event) {

        this.enabled = false;
    }
}
