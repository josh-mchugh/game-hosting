package com.example.demo.web.project.create.command;

import com.example.demo.project.aggregate.command.ProjectRegionAddCommand;
import com.example.demo.web.project.create.command.model.ProjectAddRegionRequest;
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
public class ProjectCreateCommandServiceHandleAddRegionTest {

    @Autowired
    private IProjectCreateCommandService service;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddRegion(null));
    }

    @Test
    public void whenParamHasNullRegionIdThenExpectException() {

        ProjectAddRegionRequest request = new ProjectAddRegionRequest(UUID.randomUUID(), null);

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddRegion(request));
    }

    @Test
    public void whenParamHasInvalidUUIDRegionIdThenExpectException() {

        ProjectAddRegionRequest request = new ProjectAddRegionRequest(UUID.randomUUID(), "");

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.handleAddRegion(request));
    }

    @Test
    public void whenRequestIsValidThenExpectNoExceptions() {

        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectRegionAddCommand.class))).thenReturn(UUID.randomUUID());

        ProjectAddRegionRequest request = new ProjectAddRegionRequest(UUID.randomUUID(), UUID.randomUUID().toString());

        Assertions.assertDoesNotThrow(() -> service.handleAddRegion(request));
    }
}
