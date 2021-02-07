package com.example.demo.web.admin.game.command.service;

import com.example.demo.game.aggregate.command.GameServerCreateCommand;
import com.example.demo.game.projection.IGameServerProjector;
import com.example.demo.web.admin.game.command.service.model.GameServerCreateRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdminGameServerCommandServiceHandleCreateTest {

    @Test
    public void whenParamIsNullThenThrowException() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        Assertions.assertThrows(NullPointerException.class, () -> service.handleGameServerCreate(null));
    }

    @Test
    public void whenParamIsValidThenExpectCommand() {

        IGameServerProjector gameServerProjector = Mockito.mock(IGameServerProjector.class);
        CommandGateway commandGateway = Mockito.mock(CommandGateway.class);

        AdminGameServerCommandService service = new AdminGameServerCommandService(gameServerProjector, commandGateway);

        service.handleGameServerCreate(GameServerCreateRequest.builder().build());

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(GameServerCreateCommand.class));
    }
}
