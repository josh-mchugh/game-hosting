package com.example.demo.web.dashboard.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.framework.web.ModalResponse;
import com.example.demo.game.entity.GameType;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeQuery;
import com.example.demo.web.dashboard.command.service.model.FetchGameIdByGameTypeResponse;
import com.example.demo.web.dashboard.form.DashboardProjectCreateForm;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardCommandController {

    private final ISessionUtil sessionUtil;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/project/create")
    public String postProjectCreateModal(Model model, @Valid @ModelAttribute("form") DashboardProjectCreateForm form, BindingResult results) throws ExecutionException, InterruptedException {

        if(results.hasErrors()) {

            return "dashboard/modal-project-create";
        }

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(form.getName())
                .gameId(getGameId(form.getGame()))
                .userId(sessionUtil.getCurrentUserId())
                .build();

        UUID projectId = commandGateway.sendAndWait(command);

        return new ModalResponse(model)
                .redirect(String.format("/project/%s", projectId))
                .build();
    }

    private UUID getGameId(GameType type) throws ExecutionException, InterruptedException {

        FetchGameIdByGameTypeQuery query = new FetchGameIdByGameTypeQuery(type);
        FetchGameIdByGameTypeResponse response = queryGateway.query(query, FetchGameIdByGameTypeResponse.class).get();

        return response.getId();
    }
}
