package com.example.demo.user.aggregate;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.user.aggregate.command.UserRecoveryTokenDeleteCommand;
import com.example.demo.user.aggregate.event.UserRecoveryTokenDeletedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregateRecoveryTokenDeleteCommandTest {

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

        UserRecoveryTokenDeleteCommand command = new UserRecoveryTokenDeleteCommand(id);
        UserRecoveryTokenDeletedEvent event = new UserRecoveryTokenDeletedEvent(id);

        fixture.givenCommands(createRegularCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserRecoveryTokenDeleteCommand command = new UserRecoveryTokenDeleteCommand(null);

        fixture.givenCommands(createRegularCommand(UUID.randomUUID()))
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
