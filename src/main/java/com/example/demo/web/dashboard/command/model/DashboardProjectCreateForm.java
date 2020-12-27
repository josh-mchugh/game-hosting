package com.example.demo.web.dashboard.command.model;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class DashboardProjectCreateForm {

    @NotBlank(message = "{error.not.blank}")
    private String name;

    @NotNull(message = "{error.not.blank}")
    private GameType game;

    @NotBlank(message = "{error.not.blank}")
    private String region;

    @NotBlank(message = "{error.not.blank}")
    private String server;

    private List<GameType> games = Lists.newArrayList(GameType.values());
    private List<String> regions = Collections.singletonList("US-EAST-VA-1");
    private List<String> servers = Arrays.asList("1cpu - 2gb ram", "2cpu - 4gb ram");
}
