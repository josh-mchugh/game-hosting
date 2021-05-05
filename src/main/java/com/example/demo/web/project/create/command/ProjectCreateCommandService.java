package com.example.demo.web.project.create.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectCreateCommandService implements IProjectCreateCommandService {

    private final CommandGateway commandGateway;
    private final ISessionUtil sessionUtil;

    @Override
    public ProjectCreateResponse handleProjectCreate(ProjectCreateRequest request) {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getProjectName())
                .gameId(UUID.fromString(request.getGameId()))
                .userId(sessionUtil.getCurrentUserId())
                .build();

        return new ProjectCreateResponse(commandGateway.sendAndWait(command));
    }
}
