package com.example.demo.awx.credential.aggregate;

import com.example.demo.awx.credential.aggregate.command.AwxCredentialCreateCommand;
import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.AwxCredentialType;
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
public class AwxCredentialAggregate {

    @AggregateIdentifier
    private UUID id;
    private String awxOrganizationId;
    private Long awxId;
    private String name;
    private String description;
    private String privateKey;
    private String passphrase;
    private AwxCredentialType type;

    @CommandHandler
    public AwxCredentialAggregate(AwxCredentialCreateCommand command, StringEncryptor encryptor) {

        AwxCredentialCreatedEvent event = AwxCredentialCreatedEvent.builder()
                .id(command.getId())
                .awxOrganizationId(command.getAwxOrganizationId())
                .awxId(command.getAwxId())
                .name(command.getName())
                .description(command.getDescription())
                .privateKey(encryptor.encrypt(command.getPrivateKey()))
                .passphrase(encryptor.encrypt(command.getPassphrase()))
                .type(command.getType())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AwxCredentialCreatedEvent event) {

        this.id = event.getId();
        this.awxOrganizationId = event.getAwxOrganizationId();
        this.awxId = event.getAwxId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.privateKey = event.getPrivateKey();
        this.passphrase = event.getPassphrase();
        this.type = event.getType();
    }
}
