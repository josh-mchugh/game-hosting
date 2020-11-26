package com.example.demo.email.template.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.templates.service.EmailTemplateService;
import com.example.demo.email.templates.service.IEmailTemplateService;
import com.example.demo.user.aggregate.event.UserRecoveryTokenEmailEvent;
import com.example.demo.util.AppUrlUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmailTemplateUserRecoveryTokenTest {

    private CommandGateway commandGateway;
    private AppUrlUtil appUrlUtil;

    @BeforeEach
    public void setup() {

        commandGateway = Mockito.mock(CommandGateway.class);
        appUrlUtil = Mockito.mock(AppUrlUtil.class);
    }

    @Test
    public void whenParamIsNullThenExpectException() {

        IEmailTemplateService service = new EmailTemplateService(appUrlUtil, commandGateway);

        Assertions.assertThrows(NullPointerException.class, () -> service.handleUserRecoveryTokenEmail(null));
    }

    @Test
    public void whenUserRecoveryTokenThenExpectCommandSent() {

        IEmailTemplateService service = new EmailTemplateService(appUrlUtil, commandGateway);

        UserRecoveryTokenEmailEvent event = UserRecoveryTokenEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();

        service.handleUserRecoveryTokenEmail(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailCreateCommand.class));
    }
}
