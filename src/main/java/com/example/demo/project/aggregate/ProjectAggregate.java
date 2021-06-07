package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectBillingAddCommand;
import com.example.demo.project.aggregate.command.ProjectStateCreateInstanceUpdateCommand;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.command.ProjectServerAddCommand;
import com.example.demo.project.aggregate.command.ProjectRegionAddCommand;
import com.example.demo.project.aggregate.event.ProjectBillingAddedEvent;
import com.example.demo.project.aggregate.event.ProjectStateCreateInstanceUpdatedEvent;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.aggregate.event.ProjectServerAddedEvent;
import com.example.demo.project.aggregate.event.ProjectRegionAddedEvent;
import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class ProjectAggregate {

    @AggregateIdentifier
    private UUID id;
    private String name;
    private ProjectStatus status;
    private ProjectState state;
    private List<Member> members;
    private UUID gameId;
    private UUID ovhRegionId;
    private UUID ovhFlavorId;
    private UUID ovhImageId;

    @Data
    @Builder(builderClassName = "Builder")
    public static class Member {

        private UUID id;
        private UUID userId;
        private ProjectMembershipRole role;
    }

    @CommandHandler
    public ProjectAggregate(ProjectCreateCommand command) {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .status(ProjectStatus.CONFIG)
                .state(ProjectState.CONFIG_REGION)
                .gameId(command.getGameId())
                .member(ProjectCreatedEvent.createOwner(command.getUserId()))
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProjectCreatedEvent event) {

        this.id = event.getId();
        this.name = event.getName();
        this.status = event.getStatus();
        this.state = event.getState();
        this.gameId = event.getGameId();

        Member member = Member.builder()
                .id(event.getMember().getId())
                .userId(event.getMember().getUserId())
                .role(event.getMember().getRole())
                .build();

        this.members = Lists.newArrayList(member);
    }

    @CommandHandler
    public void on(ProjectRegionAddCommand command) {

        ProjectRegionAddedEvent event = ProjectRegionAddedEvent.builder()
                .id(command.getId())
                .ovhRegionId(command.getOvhRegionId())
                .state(ProjectState.CONFIG_SERVER)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProjectRegionAddedEvent event) {

        this.id = event.getId();
        this.ovhRegionId = event.getOvhRegionId();
        this.state = event.getState();
    }

    @CommandHandler
    public void on(ProjectServerAddCommand command) {

        ProjectServerAddedEvent event = ProjectServerAddedEvent.builder()
                .id(command.getId())
                .ovhFlavorId(command.getOvhFlavorId())
                .ovhImageId(command.getOvhImageId())
                .state(ProjectState.CONFIG_BILLING)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProjectServerAddedEvent event) {

        this.id = event.getId();
        this.ovhFlavorId = event.getOvhFlavorId();
        this.ovhImageId = event.getOvhImageId();
        this.state = event.getState();
    }

    @CommandHandler
    public void on(ProjectBillingAddCommand command) {

        ProjectBillingAddedEvent event = ProjectBillingAddedEvent.builder()
                .id(command.getId())
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProjectBillingAddedEvent event) {

        this.id = event.getId();
        this.status = event.getStatus();
        this.state = event.getState();
    }

    @CommandHandler
    public void on(ProjectStateCreateInstanceUpdateCommand command) {

        ProjectStateCreateInstanceUpdatedEvent event = ProjectStateCreateInstanceUpdatedEvent.builder()
                .id(command.getId())
                .status(ProjectStatus.BUILD)
                .state(ProjectState.BUILD_CREATE_INSTANCE)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProjectStateCreateInstanceUpdatedEvent event) {

        this.id = event.getId();
        this.status = event.getStatus();
        this.state = event.getState();
    }
}
