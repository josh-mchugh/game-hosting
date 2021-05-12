package com.example.demo.web.project.create.command;

import com.example.demo.project.aggregate.command.ProjectFlavorAddCommand;
import com.example.demo.web.project.create.command.model.ProjectAddBillingRequest;
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
public class ProjectCreateCommandServiceHandleAddBillingTest {

    @Autowired
    private IProjectCreateCommandService service;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> service.handleAddBilling(null));
    }

    @Test
    public void whenParamHasNullIdThenDoNotExpectException() {

        ProjectAddBillingRequest request = new ProjectAddBillingRequest(null);

        Assertions.assertDoesNotThrow(() -> service.handleAddBilling(request));
    }

    @Test
    public void whenParamIsValidThenDoNotExpectException() {

        Mockito.when(commandGateway.sendAndWait(Mockito.any(ProjectFlavorAddCommand.class))).thenReturn(UUID.randomUUID());

        ProjectAddBillingRequest request = new ProjectAddBillingRequest(UUID.randomUUID());

        Assertions.assertDoesNotThrow(() -> service.handleAddBilling(request));
    }
}
