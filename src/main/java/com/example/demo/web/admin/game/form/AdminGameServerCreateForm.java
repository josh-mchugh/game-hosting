package com.example.demo.web.admin.game.form;

import com.example.demo.game.entity.GameServerStatus;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AdminGameServerCreateForm {

    @NotBlank(message = "{error.not.blank}")
    private String name;

    private String description;

    @NotNull(message = "{error.not.blank}")
    private GameServerStatus selectedStatus;

    @NotBlank(message = "{error.not.blank}")
    private String gameId;

    @NotBlank(message = "{error.not.blank}")
    private String regionId;

    @NotBlank(message = "{error.not.blank}")
    private String flavorId;

    @NotBlank(message = "{error.not.blank}")
    private String imageId;

    private List<GameServerStatus> statuses = Lists.newArrayList(GameServerStatus.values());

    public boolean isDefaultStatus(GameServerStatus status) {

        return GameServerStatus.ACTIVE.equals(status);
    }
}
