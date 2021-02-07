package com.example.demo.web.admin.game.command.service;

import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.game.projection.IGameServerProjector;
import com.example.demo.game.projection.model.ExistsGameServerByConfigQuery;
import com.example.demo.game.projection.model.ExistsGameServerByConfigResponse;
import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;
import com.example.demo.web.admin.game.command.service.model.GameServerCreateRequest;
import com.example.demo.web.admin.game.command.service.model.GameServerExistsByConfig;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminGameServerCommandService implements IAdminGameServerCommandService {

    private final IGameServerProjector gameServerProjector;
    private final CommandGateway commandGateway;

    @Override
    public void handleGameServerCreate(GameServerCreateRequest request) {

        GameServerCreateCommand command = GameServerCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .status(request.getStatus())
                .gameId(request.getGameId())
                .regionId(request.getRegionId())
                .flavorId(request.getFlavorId())
                .imageId(request.getImageId())
                .build();

        commandGateway.send(command);
    }

    @Override
    public boolean existsByName(String name) {

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery(name);
        ExistsGameServerByNameResponse response = gameServerProjector.existsByName(query);

        return response.isExists();
    }

    @Override
    public boolean existsByConfig(GameServerExistsByConfig request) {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(request.getGameId())
                .regionId(request.getRegionId())
                .flavorId(request.getFlavorId())
                .imageId(request.getImageId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        return response.isExists();
    }
}
