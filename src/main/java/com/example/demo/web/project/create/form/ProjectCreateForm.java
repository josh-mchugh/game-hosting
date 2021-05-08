package com.example.demo.web.project.create.form;

import com.example.demo.game.entity.GameType;
import com.google.common.collect.ImmutableMap;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectCreateForm {

    @NotBlank(message = "{error.not.blank}")
    private String projectName;

    @NotBlank(message = "{error.not.blank}")
    private String selectedGameId;

    private ImmutableMap<String, GameType> availableGames;
}
