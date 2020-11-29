package com.example.demo.ovh.instance.aggregate;

import com.example.demo.ovh.instance.aggregate.command.InstanceGroupCreateCommand;
import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class InstanceGroupAggregate {

    @AggregateIdentifier
    private UUID id;
    private String projectId;
    private String groupId;
    private String name;
    private String type;

    @CommandHandler
    public InstanceGroupAggregate(InstanceGroupCreateCommand command) {

        InstanceGroupCreatedEvent event = InstanceGroupCreatedEvent.builder()
                .id(command.getId())
                .projectId(command.getProjectId())
                .groupId(command.getGroupId())
                .name(command.getName())
                .type(command.getType())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(InstanceGroupCreatedEvent event) {

        this.id = event.getId();
        this.projectId = event.getProjectId();
        this.groupId = event.getGroupId();
        this.name = event.getName();
        this.type = event.getType();
    }
}
