package com.example.demo.ovh.credential.aggregate;

import com.example.demo.ovh.credential.aggregate.command.CredentialCreateCommand;
import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.CredentialType;
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
public class CredentialAggregate {

    @AggregateIdentifier
    private UUID id;
    private String ovhId;
    private String name;
    private String publicKey;
    private CredentialType type;

    @CommandHandler
    public CredentialAggregate(CredentialCreateCommand command, StringEncryptor encryptor) {

        CredentialCreatedEvent event = CredentialCreatedEvent.builder()
                .id(command.getId())
                .ovhId(command.getOvhId())
                .name(command.getName())
                .publicKey(encryptor.encrypt(command.getPublicKey()))
                .type(command.getType())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CredentialCreatedEvent event) {

        this.id = event.getId();
        this.ovhId = event.getOvhId();
        this.name = event.getPublicKey();
        this.publicKey = event.getPublicKey();
        this.type = event.getType();
    }
}
