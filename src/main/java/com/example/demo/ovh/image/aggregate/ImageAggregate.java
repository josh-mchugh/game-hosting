package com.example.demo.ovh.image.aggregate;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class ImageAggregate {

    @AggregateIdentifier
    private UUID id;
    private String regionId;
    private String imageId;
    private String name;
    private String type;
    private LocalDateTime imageCreatedDate;
    private String flavorType;
    private String hourly;
    private String monthly;
    private Double size;
    private Integer minRam;
    private Integer minDisk;
    private String username;
    private String status;
    private String visibility;

    @CommandHandler
    public ImageAggregate(ImageCreateCommand command) {

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(command.getId())
                .regionId(command.getRegionId())
                .imageId(command.getImageId())
                .name(command.getName())
                .type(command.getType())
                .imageCreatedDate(command.getImageCreatedDate())
                .flavorType(command.getFlavorType())
                .hourly(command.getHourly())
                .monthly(command.getMonthly())
                .size(command.getSize())
                .minRam(command.getMinRam())
                .minDisk(command.getMinDisk())
                .username(command.getUsername())
                .status(command.getStatus())
                .visibility(command.getVisibility())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ImageCreatedEvent event) {

        this.id = event.getId();
        this.regionId = event.getRegionId();
        this.imageId = event.getImageId();
        this.name = event.getName();
        this.type = event.getType();
        this.imageCreatedDate = event.getImageCreatedDate();
        this.flavorType = event.getFlavorType();
        this.hourly = event.getHourly();
        this.monthly = event.getMonthly();
        this.size = event.getSize();
        this.minRam = event.getMinRam();
        this.minDisk = event.getMinDisk();
        this.username = event.getUsername();
        this.status = event.getStatus();
        this.visibility = event.getVisibility();
    }

    @CommandHandler
    public void on(ImageUpdateCommand command) {

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(command.getId())
                .regionId(command.getRegionId())
                .imageId(command.getImageId())
                .name(command.getName())
                .type(command.getType())
                .imageCreatedDate(command.getImageCreatedDate())
                .flavorType(command.getFlavorType())
                .hourly(command.getHourly())
                .monthly(command.getMonthly())
                .size(command.getSize())
                .minRam(command.getMinRam())
                .minDisk(command.getMinDisk())
                .username(command.getUsername())
                .status(command.getStatus())
                .visibility(command.getVisibility())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ImageUpdatedEvent event) {

        this.id = event.getId();
        this.regionId = event.getRegionId();
        this.imageId = event.getImageId();
        this.name = event.getName();
        this.type = event.getType();
        this.imageCreatedDate = event.getImageCreatedDate();
        this.flavorType = event.getFlavorType();
        this.hourly = event.getHourly();
        this.monthly = event.getMonthly();
        this.size = event.getSize();
        this.minRam = event.getMinRam();
        this.minDisk = event.getMinDisk();
        this.username = event.getUsername();
        this.status = event.getStatus();
        this.visibility = event.getVisibility();
    }
}
