package com.example.demo.web.project.create.form;

import com.example.demo.game.entity.GameType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class ProjectCreateForm {

    @NotBlank(message = "{error.not.blank}")
    private String projectName;

    @NotBlank(message = "{error.not.blank}")
    private String selectedGameId;

    private Map<String, GameType> availableGames;
}
