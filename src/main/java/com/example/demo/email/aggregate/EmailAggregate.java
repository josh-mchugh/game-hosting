package com.example.demo.email.aggregate;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.aggregate.command.EmailFailCommand;
import com.example.demo.email.aggregate.command.EmailSentCommand;
import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.aggregate.event.EmailFailedEvent;
import com.example.demo.email.aggregate.event.EmailSentEvent;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.EmailTemplateType;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class EmailAggregate {

    @AggregateIdentifier
    private UUID id;
    private EmailTemplateType template;
    private EmailStatus status;
    private String toAddress;
    private Map<String, Object> bodyContext;
    private List<Object> subjectContext;
    private LocalDateTime sentDate;

    @CommandHandler
    public EmailAggregate(EmailCreateCommand command) {

        EmailCreatedEvent event = EmailCreatedEvent.builder()
                .id(command.getId())
                .template(command.getTemplate())
                .toAddress(command.getToAddress())
                .bodyContext(command.getBodyContext())
                .subjectContext(command.getSubjectContext())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EmailCreatedEvent event) {

        this.id = event.getId();
        this.template = event.getTemplate();
        this.status = EmailStatus.QUEUED;
        this.toAddress = event.getToAddress();
        this.bodyContext = event.getBodyContext();
        this.subjectContext = event.getSubjectContext();
    }

    @CommandHandler
    public void on(EmailSentCommand command) {

        EmailSentEvent event = EmailSentEvent.builder()
                .id(command.getId())
                .sentDate(command.getSentDate())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EmailSentEvent event) {

        this.status = EmailStatus.SENT;
        this.sentDate = event.getSentDate();
    }

    @CommandHandler
    public void on(EmailFailCommand command) {

        EmailFailedEvent event = EmailFailedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(EmailFailedEvent event) {

        this.status = EmailStatus.FAILED;
    }
}
