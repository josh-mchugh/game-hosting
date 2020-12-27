package com.example.demo.web.dashboard.command.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.game.entity.GameType;
import com.example.demo.game.entity.model.Game;
import com.example.demo.game.projection.IGameProjection;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.user.entity.model.User;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class DashboardCommandServiceProjectCreateTest {

    @Autowired
    private IDashboardCommandService dashboardService;

    @MockBean
    private IGameProjection gameProjection;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenProjectCreateProjectIsValidThenReturnProjectId() {

        User user = User.builder()
                .id("testId")
                .build();

        Game game = Game.builder()
                .type(GameType.MINECRAFT_JAVA)
                .build();

        Mockito.when(gameProjection.getGameByType(Mockito.any(GameType.class))).thenReturn(game);
        Mockito.when(sessionUtil.getCurrentUser()).thenReturn(user);
        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectCreateCommand.class))).thenReturn(UUID.randomUUID());

        DashboardProjectCreateRequest createRequest = DashboardProjectCreateRequest.builder()
                .name("test-project")
                .flavor("flavor-id")
                .region("region-id")
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

        DashboardProjectCreateResponse response = dashboardService.handleProjectCreate(createRequest);

        Assertions.assertNotNull(response.getProjectId());
    }

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> dashboardService.handleProjectCreate(null));
    }
}
