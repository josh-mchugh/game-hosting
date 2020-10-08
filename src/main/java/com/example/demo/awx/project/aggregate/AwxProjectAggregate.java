package com.example.demo.awx.project.aggregate;

import com.example.demo.awx.project.aggregate.command.AwxProjectCreateCommand;
import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jasypt.encryption.StringEncryptor;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class AwxProjectAggregate {

    @AggregateIdentifier
    private UUID id;
    private Long organizationId;
    private String awxCredentialId;
    private Long projectId;
    private String name;
    private String description;
    private String scmType;
    private String scmUrl;
    private String scmBranch;

    @CommandHandler
    public AwxProjectAggregate(AwxProjectCreateCommand command, StringEncryptor encryptor) {

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(command.getId())
                .organizationId(command.getOrganizationId())
                .awxCredentialId(command.getAwxCredentialId())
                .projectId(command.getProjectId())
                .name(command.getName())
                .description(command.getDescription())
                .scmType(encryptor.encrypt(command.getScmType()))
                .scmUrl(encryptor.encrypt(command.getScmUrl()))
                .scmBranch(encryptor.encrypt(command.getScmBranch()))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxProjectCreatedEvent event) {

        this.id = event.getId();
        this.organizationId = event.getOrganizationId();
        this.awxCredentialId = event.getAwxCredentialId();
        this.projectId = event.getProjectId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.scmType = event.getScmType();
        this.scmUrl = event.getScmUrl();
        this.scmBranch = event.getScmBranch();
    }
}
