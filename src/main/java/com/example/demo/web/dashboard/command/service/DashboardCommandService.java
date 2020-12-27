package com.example.demo.web.dashboard.command.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.projection.IGameProjection;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DashboardCommandService implements IDashboardCommandService {

    private final ISessionUtil sessionUtil;
    private final IGameProjection gameProjection;
    private final CommandGateway commandGateway;

    @Override
    public DashboardProjectCreateResponse handleProjectCreate(DashboardProjectCreateRequest request) {

        Game game = gameProjection.getGameByType(request.getGameType());

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .gameId(game.getId())
                .userId(sessionUtil.getCurrentUser().getId())
                .build();

        UUID projectId = commandGateway.sendAndWait(command);

        return new DashboardProjectCreateResponse(projectId.toString());
    }
}
