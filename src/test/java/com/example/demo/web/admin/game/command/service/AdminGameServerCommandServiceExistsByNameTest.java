package com.example.demo.web.admin.game.command.service;

import com.example.demo.game.projection.IGameServerProjector;
import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdminGameServerCommandServiceExistsByNameTest {

    @Test
    public void whenEntityExistsThenReturnTrue() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        ExistsGameServerByNameResponse response = new ExistsGameServerByNameResponse(true);
        Mockito.when(gameServerProjector.existsByName(Mockito.any(ExistsGameServerByNameQuery.class))).thenReturn(response);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertTrue(service.existsByName("name"));
    }

    @Test
    public void whenEntityNotExistsThenReturnFalse() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        ExistsGameServerByNameResponse response = new ExistsGameServerByNameResponse(false);
        Mockito.when(gameServerProjector.existsByName(Mockito.any(ExistsGameServerByNameQuery.class))).thenReturn(response);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertFalse(service.existsByName("name"));
    }
}
