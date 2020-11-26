package com.example.demo.email.template.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.templates.service.EmailTemplateService;
import com.example.demo.email.templates.service.IEmailTemplateService;
import com.example.demo.user.aggregate.event.UserVerifyResetEmailEvent;
import com.example.demo.util.AppUrlUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmailTemplateUserVerifyResetTest {

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

        Assertions.assertThrows(NullPointerException.class, () -> service.handleUserVerifyResetEmail(null));
    }

    @Test
    public void whenUserVerifyResetPasswordThenExpectCommandSend() {

        IEmailTemplateService service = new EmailTemplateService(appUrlUtil, commandGateway);

        UserVerifyResetEmailEvent event = UserVerifyResetEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();

        service.handleUserVerifyResetEmail(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailCreateCommand.class));
    }
}
