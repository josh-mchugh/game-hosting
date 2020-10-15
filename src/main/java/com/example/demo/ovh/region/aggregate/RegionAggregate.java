package com.example.demo.ovh.region.aggregate;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.aggregate.event.RegionUpdatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class RegionAggregate {

    @AggregateIdentifier
    private UUID id;
    private String name;
    private String continentCode;
    private String countryCodes;
    private String dataCenterLocation;
    private RegionStatus status;

    @CommandHandler
    public RegionAggregate(RegionCreateCommand command) {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .continentCode(command.getContinentCode())
                .countryCodes(command.getCountryCodes())
                .dataCenterLocation(command.getDataCenterLocation())
                .status(command.getStatus())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RegionCreatedEvent event) {

        this.id = event.getId();
        this.name = event.getName();
        this.continentCode = event.getContinentCode();
        this.countryCodes = event.getCountryCodes();
        this.dataCenterLocation = event.getDataCenterLocation();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void on(RegionUpdateCommand command) {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(command.getId())
                .continentCode(command.getContinentCode())
                .countryCodes(command.getCountryCodes())
                .dataCenterLocation(command.getDataCenterLocation())
                .status(command.getStatus())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RegionUpdatedEvent event) {

        this.id = event.getId();
        this.continentCode = event.getContinentCode();
        this.countryCodes = event.getCountryCodes();
        this.dataCenterLocation = event.getDataCenterLocation();
        this.status = event.getStatus();
    }
}
