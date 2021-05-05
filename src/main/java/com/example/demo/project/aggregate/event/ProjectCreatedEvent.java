package com.example.demo.project.aggregate.event;

import com.example.demo.project.entity.ProjectMembershipRole;
import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreatedEvent {

    UUID id;
    String name;
    ProjectStatus status;
    ProjectState state;
    UUID gameId;
    Member member;

    @Value
    @lombok.Builder(builderClassName = "Builder")
    public static class Member {

        UUID id;
        UUID userId;
        ProjectMembershipRole role;
    }

    public static Member createOwner(UUID userId) {

        return createMember(userId, ProjectMembershipRole.OWNER);
    }

    public static Member createMember(UUID userId, ProjectMembershipRole role) {

        return Member.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .role(role)
                .build();
    }
}
