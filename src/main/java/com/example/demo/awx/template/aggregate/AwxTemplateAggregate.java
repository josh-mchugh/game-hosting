package com.example.demo.awx.template.aggregate;

import com.example.demo.awx.template.aggregate.command.AwxTemplateCreateCommand;
import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.TemplateJobType;
import com.example.demo.awx.template.entity.TemplateVerbosity;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxTemplateAggregate {

    @AggregateIdentifier
    private UUID id;
    private String awxCredentialId;
    private String awxInventoryId;
    private String awxPlaybookId;
    private Long templateId;
    private String name;
    private String description;
    private TemplateJobType jobType;
    private TemplateVerbosity verbosity;

    @CommandHandler
    public AwxTemplateAggregate(AwxTemplateCreateCommand command) {

        AwxTemplateCreatedEvent event = AwxTemplateCreatedEvent.builder()
                .id(command.getId())
                .awxCredentialId(command.getAwxCredentialId())
                .awxInventoryId(command.getAwxInventoryId())
                .awxPlaybookId(command.getAwxPlaybookId())
                .templateId(command.getTemplateId())
                .name(command.getName())
                .description(command.getDescription())
                .jobType(command.getJobType())
                .verbosity(command.getVerbosity())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxTemplateCreatedEvent event) {

        this.id = event.getId();
        this.awxCredentialId = event.getAwxCredentialId();
        this.awxInventoryId = event.getAwxInventoryId();
        this.awxPlaybookId = event.getAwxPlaybookId();
        this.templateId = event.getTemplateId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.jobType = event.getJobType();
        this.verbosity = event.getVerbosity();
    }
}
