package com.example.demo.web.project.create.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.project.aggregate.command.ProjectCreateCommand;
import com.example.demo.web.project.create.command.model.ProjectCreateRequest;
import com.example.demo.web.project.create.command.model.ProjectCreateResponse;
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

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ProjectCreateCommandServiceHandleCreateTest {

    @Autowired
    private IProjectCreateCommandService service;

    @MockBean
    private ISessionUtil sessionUtil;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleCreate(null));
    }

    @Test
    public void whenParamIsValidThenExpectUUID() {

        UUID id = UUID.randomUUID();

        Mockito.when(sessionUtil.getCurrentUserId()).thenReturn(UUID.randomUUID());
        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectCreateCommand.class)))
                .thenReturn(id);

        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .projectName("projectName")
                .gameId(UUID.randomUUID().toString())
                .build();

        ProjectCreateResponse response = service.handleCreate(request);

        Assertions.assertEquals(id, response.getId());
    }

    @Test
    public void whenParamIsHasNullGameIdThenThrowException() {

        Mockito.when(sessionUtil.getCurrentUserId()).thenReturn(UUID.randomUUID());

        ProjectCreateRequest request = ProjectCreateRequest.builder().build();

        Assertions.assertThrows(NullPointerException.class, () -> service.handleCreate(request));
    }

    @Test
    public void whenParamIsNotUuidStringThenThrowException() {

        Mockito.when(sessionUtil.getCurrentUserId()).thenReturn(UUID.randomUUID());

        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .projectName("projectName")
                .gameId("gameId")
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.handleCreate(request));
    }
}
