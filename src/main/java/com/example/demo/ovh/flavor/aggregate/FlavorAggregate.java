package com.example.demo.ovh.flavor.aggregate;

import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.command.FlavorUpdateCommand;
import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.aggregate.event.FlavorUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class FlavorAggregate {

    @AggregateIdentifier
    private UUID id;
    private UUID regionId;
    private String ovhId;
    private String name;
    private String type;
    private Boolean available;
    private String hourly;
    private String monthly;
    private Integer quota;
    private String osType;
    private Integer vcpus;
    private Integer ram;
    private Integer disk;
    private Integer inboundBandwidth;
    private Integer outboundBandwidth;

    @CommandHandler
    public FlavorAggregate(FlavorCreateCommand command) {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(command.getId())
                .regionId(command.getRegionId())
                .ovhId(command.getOvhId())
                .name(command.getName())
                .type(command.getType())
                .available(command.getAvailable())
                .hourly(command.getHourly())
                .monthly(command.getMonthly())
                .quota(command.getQuota())
                .osType(command.getOsType())
                .vcpus(command.getVcpus())
                .ram(command.getRam())
                .disk(command.getDisk())
                .inboundBandwidth(command.getInboundBandwidth())
                .outboundBandwidth(command.getOutboundBandwidth())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FlavorCreatedEvent event) {

        this.id = event.getId();
        this.regionId = event.getRegionId();
        this.ovhId = event.getOvhId();
        this.name = event.getName();
        this.type = event.getType();
        this.available = event.getAvailable();
        this.hourly = event.getHourly();
        this.monthly = event.getMonthly();
        this.quota = event.getQuota();
        this.osType = event.getOsType();
        this.vcpus = event.getVcpus();
        this.ram = event.getRam();
        this.disk = event.getDisk();
        this.inboundBandwidth = event.getInboundBandwidth();
        this.outboundBandwidth = event.getOutboundBandwidth();
    }

    @CommandHandler
    public void on(FlavorUpdateCommand command) {

        FlavorUpdatedEvent event = FlavorUpdatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .type(command.getType())
                .available(command.getAvailable())
                .hourly(command.getHourly())
                .monthly(command.getMonthly())
                .quota(command.getQuota())
                .osType(command.getOsType())
                .vcpus(command.getVcpus())
                .ram(command.getRam())
                .disk(command.getDisk())
                .inboundBandwidth(command.getInboundBandwidth())
                .outboundBandwidth(command.getOutboundBandwidth())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FlavorUpdatedEvent event) {

        this.id = event.getId();
        this.name = event.getName();
        this.type = event.getType();
        this.available = event.getAvailable();
        this.hourly = event.getHourly();
        this.monthly = event.getMonthly();
        this.quota = event.getQuota();
        this.osType = event.getOsType();
        this.vcpus = event.getVcpus();
        this.ram = event.getRam();
        this.disk = event.getDisk();
        this.inboundBandwidth = event.getInboundBandwidth();
        this.outboundBandwidth = event.getOutboundBandwidth();
    }
}
