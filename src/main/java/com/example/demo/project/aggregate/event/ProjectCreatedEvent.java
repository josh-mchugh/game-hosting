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
    String gameId;
    Member member;

    @Value
    @lombok.Builder(builderClassName = "Builder")
    public static class Member {

        UUID id;
        String userId;
    }

    public static Member createMember(String userId) {

        return Member.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .build();
    }
}
