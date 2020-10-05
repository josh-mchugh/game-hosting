package com.example.demo.awx.playbook.aggregate;

import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;
import com.example.demo.awx.playbook.entity.PlaybookType;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxPlaybookAggregate {

    @AggregateIdentifier
    private UUID id;
    private String awxProjectId;
    private String name;
    private PlaybookType type;

    @CommandHandler
    public AwxPlaybookAggregate (AwxPlaybookCreateCommand command){

        AwxPlaybookCreatedEvent event = AwxPlaybookCreatedEvent.builder()
                .id(command.getId())
                .awxProjectId(command.getAwxProjectId())
                .name(command.getName())
                .type(parseType(command.getName()))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxPlaybookCreatedEvent event) {

        this.id = event.getId();
        this.awxProjectId = event.getAwxProjectId();
        this.name = event.getName();
        this.type = event.getType();
    }

    private PlaybookType parseType(String name) {

        String value = StringUtils.substringBefore(name, "-playbook.yml");

        if(value.equals(name)) {

            throw new IllegalArgumentException("Unable to parse and convert playbook name to PlaybookType");
        }

        return PlaybookType.valueOf(value.replace("-", "_").toUpperCase());
    }
}
