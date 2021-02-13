package com.example.demo.project.aggregate.event;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class ProjectCreatedEvent {

    UUID id;
    String name;
    UUID gameId;
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
