package com.example.demo.game.aggregate;

import com.example.demo.game.aggregate.command.GameCreateCommand;
import com.example.demo.game.aggregate.event.GameCreatedEvent;
import com.example.demo.game.entity.GameType;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class GameAggregate {

    @AggregateIdentifier
    private UUID id;
    private GameType type;

    @CommandHandler
    public GameAggregate(GameCreateCommand command) {

        GameCreatedEvent event = new GameCreatedEvent(command.getId(), command.getType());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(GameCreatedEvent event) {

        this.id = event.getId();
        this.type = event.getType();
    }
}
