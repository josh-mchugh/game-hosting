package com.example.demo.game.aggregate;

import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.game.aggregate.event.GameServerCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class GameServerAggregate {

    @AggregateIdentifier
    private UUID id;
    private UUID gameId;
    private UUID regionId;
    private UUID flavorId;
    private UUID imageId;
    private String name;
    private String description;

    @CommandHandler
    public GameServerAggregate(GameServerCreateCommand command) {

        GameServerCreatedEvent event = GameServerCreatedEvent.builder()
                .id(command.getId())
                .gameId(command.getGameId())
                .regionId(command.getRegionId())
                .flavorId(command.getFlavorId())
                .imageId(command.getImageId())
                .name(command.getName())
                .description(command.getDescription())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(GameServerCreatedEvent event) {

        this.id = event.getId();
        this.gameId = event.getGameId();
        this.regionId = event.getRegionId();
        this.flavorId = event.getFlavorId();
        this.imageId = event.getImageId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
