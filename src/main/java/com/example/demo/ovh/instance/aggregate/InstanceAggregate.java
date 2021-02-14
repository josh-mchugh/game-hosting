package com.example.demo.ovh.instance.aggregate;

import com.example.demo.ovh.instance.aggregate.command.InstanceCreateCommand;
import com.example.demo.ovh.instance.aggregate.command.InstanceUpdateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class InstanceAggregate {

    @AggregateIdentifier
    private UUID id;
    private UUID flavorId;
    private UUID imageId;
    private UUID instanceGroupId;
    private UUID credentialId;
    private String ovhId;
    private String name;
    private InstanceStatus status;
    private LocalDateTime instanceCreatedDate;
    private String ip4Address;
    private String ip6Address;

    @CommandHandler
    public InstanceAggregate(InstanceCreateCommand command) {

        InstanceCreatedEvent event = InstanceCreatedEvent.builder()
                .id(command.getId())
                .flavorId(command.getFlavorId())
                .imageId(command.getImageId())
                .instanceGroupId(command.getInstanceGroupId())
                .credentialId(command.getCredentialId())
                .ovhId(command.getOvhId())
                .name(command.getName())
                .status(command.getStatus())
                .instanceCreatedDate(command.getInstanceCreatedDate())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(InstanceCreatedEvent event) {

        this.id = event.getId();
        this.flavorId = event.getFlavorId();
        this.imageId = event.getImageId();
        this.instanceGroupId = event.getInstanceGroupId();
        this.credentialId = event.getCredentialId();
        this.ovhId = event.getOvhId();
        this.name = event.getName();
        this.status = event.getStatus();
        this.instanceCreatedDate = event.getInstanceCreatedDate();
    }

    @CommandHandler
    public void on(InstanceUpdateCommand command) {

        InstanceUpdatedEvent event = InstanceUpdatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .status(command.getStatus())
                .ip4Address(command.getIp4Address())
                .ip6Address(command.getIp6Address())
                .instanceCreatedDate(command.getInstanceCreatedDate())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(InstanceUpdatedEvent event) {

        this.name = event.getName();
        this.status = event.getStatus();
        this.ip4Address = event.getIp4Address();
        this.ip6Address = event.getIp6Address();
        this.instanceCreatedDate = event.getInstanceCreatedDate();
    }
}
