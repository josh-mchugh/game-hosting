package com.example.demo.email.template.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.templates.service.EmailTemplateServiceImpl;
import com.example.demo.email.templates.service.EmailTemplateService;
import com.example.demo.user.aggregate.event.UserWelcomeEmailEvent;
import com.example.demo.util.AppUrlUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmailTemplateUserWelcomeTest {

    private CommandGateway commandGateway;
    private AppUrlUtil appUrlUtil;

    @BeforeEach
    public void setup() {

        commandGateway = Mockito.mock(CommandGateway.class);
        appUrlUtil = Mockito.mock(AppUrlUtil.class);
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        EmailTemplateService service = new EmailTemplateServiceImpl(appUrlUtil, commandGateway);

        Assertions.assertThrows(NullPointerException.class, () -> service.handleUserWelcomeEmail(null));
    }

    @Test
    public void whenUserWelcomeEmailExpectCommandSent() {

        EmailTemplateService service = new EmailTemplateServiceImpl(appUrlUtil, commandGateway);

        UserWelcomeEmailEvent event = new UserWelcomeEmailEvent("test@test");

        service.handleUserWelcomeEmail(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailCreateCommand.class));
    }
}
