package com.example.demo.web.project.create.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.project.aggregate.command.ProjectBillingAddCommand;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.project.aggregate.command.ProjectServerAddCommand;
import com.example.demo.project.aggregate.command.ProjectRegionAddCommand;
import com.example.demo.web.project.create.command.model.ProjectAddBillingRequest;
import com.example.demo.web.project.create.command.model.ProjectAddServerRequest;
import com.example.demo.web.project.create.command.model.ProjectAddRegionRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectImageIdQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectImageIdResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ProjectCreateCommandService implements IProjectCreateCommandService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final ISessionUtil sessionUtil;

    @Override
    public ProjectCreateResponse handleCreate(ProjectCreateRequest request) {

        ProjectCreateCommand command = ProjectCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getProjectName())
                .gameId(UUID.fromString(request.getGameId()))
                .userId(sessionUtil.getCurrentUserId())
                .build();

        return new ProjectCreateResponse(commandGateway.sendAndWait(command));
    }

    @Override
    public void handleAddRegion(ProjectAddRegionRequest request) {

        ProjectRegionAddCommand command = ProjectRegionAddCommand.builder()
                .id(request.getId())
                .ovhRegionId(UUID.fromString(request.getSelectedRegionId()))
                .build();

        commandGateway.sendAndWait(command);
    }

    @Override
    public void handleAddServer(ProjectAddServerRequest request) throws ExecutionException, InterruptedException {

        FetchProjectImageIdQuery query = new FetchProjectImageIdQuery(request.getId(), request.getSelectedFlavorId());
        FetchProjectImageIdResponse response = queryGateway.query(query, FetchProjectImageIdResponse.class).get();

        ProjectServerAddCommand command = ProjectServerAddCommand.builder()
                .id(request.getId())
                .ovhFlavorId(UUID.fromString(request.getSelectedFlavorId()))
                .ovhImageId(response.getImageId())
                .build();

        commandGateway.sendAndWait(command);
    }

    @Override
    public void handleAddBilling(ProjectAddBillingRequest request) {

        commandGateway.sendAndWait(new ProjectBillingAddCommand(request.getId()));
    }
}
