package com.example.demo.user.aggregate;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.user.aggregate.event.UserPasswordChangedEmailEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregatePasswordChangeCommandTest {

    private FixtureConfiguration<UserAggregate> fixture;

    @BeforeEach
    public void setup() {

        PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("");

        fixture = new AggregateTestFixture<>(UserAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(encoder);
    }

    @Test
    public void whenCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(id)
                .password("password")
                .build();

        UserPasswordChangedEvent event = UserPasswordChangedEvent.builder()
                .id(id)
                .password("")
                .build();

        UserPasswordChangedEmailEvent emailEvent = UserPasswordChangedEmailEvent.builder()
                .email("test@test")
                .build();

        fixture.givenCommands(createRegularCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event, emailEvent);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(null)
                .build();

        fixture.givenCommands(createRegularCommand(UUID.randomUUID()))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullPasswordThenExpectException() {

        UUID id = UUID.randomUUID();

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(id)
                .password(null)
                .build();

        fixture.givenCommands(createRegularCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankPasswordThenExpectException() {

        UUID id = UUID.randomUUID();

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(id)
                .password("")
                .build();

        fixture.givenCommands(createRegularCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private UserCreateRegularCommand createRegularCommand(UUID id) {

        return UserCreateRegularCommand.builder()
                .id(id)
                .email("test@test")
                .password("password")
                .build();
    }
}
