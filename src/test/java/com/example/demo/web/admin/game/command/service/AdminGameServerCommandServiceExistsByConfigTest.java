package com.example.demo.web.admin.game.command.service;

import com.example.demo.game.projection.IGameServerProjector;
import com.example.demo.game.projection.model.ExistsGameServerByConfigQuery;
import com.example.demo.game.projection.model.ExistsGameServerByConfigResponse;
import com.example.demo.web.admin.game.command.service.model.GameServerExistsByConfig;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdminGameServerCommandServiceExistsByConfigTest {

    @Test
    public void whenParamIsNullThenExpectException() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertThrows(NullPointerException.class, () -> service.existsByConfig(null));
    }

    @Test
    public void whenEntityExistsThenReturnTrue() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        ExistsGameServerByConfigResponse response = new ExistsGameServerByConfigResponse(true);
        Mockito.when(gameServerProjector.existsByConfig(Mockito.any(ExistsGameServerByConfigQuery.class))).thenReturn(response);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertTrue(service.existsByConfig(GameServerExistsByConfig.builder().build()));
    }

    @Test
    public void whenEntityNotExistsThenReturnFalse() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        ExistsGameServerByConfigResponse response = new ExistsGameServerByConfigResponse(false);
        Mockito.when(gameServerProjector.existsByConfig(Mockito.any(ExistsGameServerByConfigQuery.class))).thenReturn(response);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertFalse(service.existsByConfig(GameServerExistsByConfig.builder().build()));
    }
}
