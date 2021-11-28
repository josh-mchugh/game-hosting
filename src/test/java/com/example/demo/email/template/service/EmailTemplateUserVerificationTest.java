package com.example.demo.email.template.service;

import com.example.demo.email.aggregate.command.EmailCreateCommand;
import com.example.demo.email.templates.service.EmailTemplateServiceImpl;
import com.example.demo.email.templates.service.EmailTemplateService;
import com.example.demo.user.aggregate.event.UserVerificationEmailEvent;
import com.example.demo.util.AppUrlUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmailTemplateUserVerificationTest {

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

        Assertions.assertThrows(NullPointerException.class, () -> service.handleUserVerificationEmail(null));
    }

    @Test
    public void whenUserVerificationEmailThenExpectCommandSent() {

        EmailTemplateService service = new EmailTemplateServiceImpl(appUrlUtil, commandGateway);

        UserVerificationEmailEvent event = UserVerificationEmailEvent.builder()
                .email("test@test")
                .token("token")
                .build();

        service.handleUserVerificationEmail(event);

        Mockito.verify(commandGateway, Mockito.times(1)).send(Mockito.any(EmailCreateCommand.class));
    }
}
