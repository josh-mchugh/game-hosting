package com.example.demo.web.admin.game.command;

import com.example.demo.framework.web.ModalResponse;
import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.util.IMessageUtil;
import com.example.demo.web.admin.game.form.AdminGameServerCreateForm;
import com.example.demo.web.admin.game.projection.service.model.ExistsGameServerByNameQuery;
import com.example.demo.web.admin.game.projection.service.model.ExistsGameServerByNameResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/admin/game-servers")
@RequiredArgsConstructor
public class AdminGameServerCommandController {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final IMessageUtil messageUtil;

    @PostMapping("/create")
    public String postCreateModal(Model model, @Valid @ModelAttribute("form") AdminGameServerCreateForm form, BindingResult result) throws ExecutionException, InterruptedException {

        if(StringUtils.isNotBlank(form.getName())) {

            ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery(form.getName());
            ExistsGameServerByNameResponse response = queryGateway.query(query, ExistsGameServerByNameResponse.class).get();

            if (response.isExists()) {

                result.rejectValue("name", "error.name.exists", "Name already exists");
            }
        }

        if(result.hasErrors()) {

            return "admin/game/partial/modal-game-server-create";
        }

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(form.getName())
                .description(form.getDescription())
                .status(form.getSelectedStatus())
                .gameId(UUID.fromString(form.getGameId()))
                .regionId(UUID.fromString(form.getRegionId()))
                .flavorId(UUID.fromString(form.getFlavorId()))
                .imageId(UUID.fromString(form.getImageId()))
                .build();

        commandGateway.send(command);

        return new ModalResponse(model)
                .toast(ModalResponse.Type.SUCCESS, messageUtil.getMessage("message.admin.game.server.create.modal.success"))
                .event(ModalResponse.RELOAD, "gameServersTable")
                .build();
    }
}
