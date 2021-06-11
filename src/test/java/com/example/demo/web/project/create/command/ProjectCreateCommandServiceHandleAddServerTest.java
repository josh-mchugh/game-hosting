package com.example.demo.web.project.create.command;

import com.example.demo.project.aggregate.command.ProjectServerAddCommand;
import com.example.demo.web.project.create.command.model.ProjectAddServerRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ProjectCreateCommandServiceHandleAddServerTest {

    @Autowired
    private IProjectCreateCommandService service;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddServer(null));
    }

    @Test
    public void whenParamOvhFlavorIdIsNullThenExpectException() {

        ProjectAddServerRequest request = new ProjectAddServerRequest(UUID.randomUUID(), null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.handleAddServer(request));
    }

    @Test
    public void whenParamOvhFlavorIdIsInvalidUUIDThenExpectException() {

        ProjectAddServerRequest request = new ProjectAddServerRequest(UUID.randomUUID(), "");

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.handleAddServer(request));
    }

    @Test
    public void whenParamIsValidThenExpectNoException() {

        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectServerAddCommand.class))).thenReturn(UUID.randomUUID());

        ProjectAddServerRequest request = new ProjectAddServerRequest(UUID.randomUUID(), UUID.randomUUID().toString());

        Assertions.assertDoesNotThrow(() -> service.handleAddServer(request));
    }
}
