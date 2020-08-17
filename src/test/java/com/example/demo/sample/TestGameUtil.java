package com.example.demo.sample;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.service.model.GameCreateRequest;

public class TestGameUtil {

    public enum Type {
        MC_BEDROCK,
        MC_JAVA
    }

    public static Builder builder() {

        return new Builder(Type.MC_JAVA);
    }

    public static Builder builder(Type type) {

        return new Builder(type);
    }

    public static class Builder {

        private final GameCreateRequest.Builder builder;

        public Builder(Type type) {

            builder = getByType(type);
        }

        public GameCreateRequest build() {

            return builder.build();
        }
    }

    private static GameCreateRequest.Builder getByType(Type type) {

        switch (type) {
            case MC_BEDROCK:
                return buildMC_BedRockType();
            default:
                return buildDefaultType();
        }
    }

    private static GameCreateRequest.Builder buildDefaultType() {

        return GameCreateRequest.builder()
                .type(GameType.MINECRAFT_JAVA);
    }

    private static GameCreateRequest.Builder buildMC_BedRockType() {

        return GameCreateRequest.builder()
                .type(GameType.MINECRAFT_BEDROCK);
    }
}
