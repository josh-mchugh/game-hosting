package com.example.demo.sample.util;

import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.project.service.model.ProjectCreateRequest;
import com.example.demo.user.model.User;

public class TestProjectCreateRequest {

    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {

        private final ProjectCreateRequest.Builder builder;

        public Builder() {

            builder = ProjectCreateRequest.builder();
        }

        public Builder name(String name) {

            builder.name(name);

            return this;
        }

        public Builder userId(User user) {

            return userId(user.getId());
        }

        public Builder userId(String userId) {

            builder.userId(userId);

            return this;
        }

        public Builder gameType(Game game) {

            builder.gameType(game.getType());

            return this;
        }

        public Builder gameType(GameType gameType) {

            builder.gameType(GameType.MINECRAFT_JAVA);

            return this;
        }

        public ProjectCreateRequest build() {

            return this.builder.build();
        }
    }
}
