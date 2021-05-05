package com.example.demo.project.aggregate;

import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
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
    private UUID gameId;
    private String name;
    private ProjectStatus status;
    private ProjectState state;
    private List<Member> members;

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
}
