package com.example.demo.web.admin.game.command;

import com.example.demo.framework.web.ModalResponse;
import com.example.demo.util.IMessageUtil;
import com.example.demo.web.admin.game.command.service.IAdminGameServerCommandService;
import com.example.demo.web.admin.game.command.service.model.GameServerCreateRequest;
import com.example.demo.web.admin.game.form.AdminGameServerCreateForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/admin/game-servers")
@RequiredArgsConstructor
public class AdminGameServerCommandController {

    private final IAdminGameServerCommandService gameServerService;
    private final IMessageUtil messageUtil;

    @PostMapping("/create")
    public String postCreateModal(Model model, @Valid @ModelAttribute("form") AdminGameServerCreateForm form, BindingResult result) {

        if(StringUtils.isNotBlank(form.getName())) {

            if (gameServerService.existsByName(form.getName())) {

                result.rejectValue("name", "error.name.exists", "Name already exists");
            }
        }

        if(result.hasErrors()) {

            return "admin/game/partial/modal-game-server-create";
        }

        GameServerCreateRequest request = GameServerCreateRequest.builder()
                .name(form.getName())
                .description(form.getDescription())
                .status(form.getSelectedStatus())
                .gameId(UUID.fromString(form.getGameId()))
                .regionId(UUID.fromString(form.getRegionId()))
                .flavorId(UUID.fromString(form.getFlavorId()))
                .imageId(UUID.fromString(form.getImageId()))
                .build();

        gameServerService.handleGameServerCreate(request);

        return new ModalResponse(model)
                .toast(ModalResponse.Type.SUCCESS, messageUtil.getMessage("message.admin.game.server.create.modal.success"))
                .event(ModalResponse.RELOAD, "gameServersTable")
                .build();
    }
}
