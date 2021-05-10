package com.example.demo.web.project.create.command;

import com.example.demo.project.aggregate.command.ProjectFlavorAddCommand;
import com.example.demo.web.project.create.command.model.ProjectAddFlavorRequest;
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
public class ProjectCreateCommandServiceHandleAddFlavorTest {

    @Autowired
    private IProjectCreateCommandService service;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddFlavor(null));
    }

    @Test
    public void whenParamOvhFlavorIdIsNullThenExpectException() {

        ProjectAddFlavorRequest request = new ProjectAddFlavorRequest(UUID.randomUUID(), null);

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddFlavor(request));
    }

    @Test
    public void whenParamOvhFlavorIdIsInvalidUUIDThenExpectException() {

        ProjectAddFlavorRequest request = new ProjectAddFlavorRequest(UUID.randomUUID(), "");

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.handleAddFlavor(request));
    }

    @Test
    public void whenParamIsValidThenExpectNoException() {

        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectFlavorAddCommand.class))).thenReturn(UUID.randomUUID());

        ProjectAddFlavorRequest request = new ProjectAddFlavorRequest(UUID.randomUUID(), UUID.randomUUID().toString());

        Assertions.assertDoesNotThrow(() -> service.handleAddFlavor(request));
    }
}
