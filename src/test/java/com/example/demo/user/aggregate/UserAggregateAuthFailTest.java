package com.example.demo.user.aggregate;

import com.example.demo.user.aggregate.command.UserAuthFailCommand;
import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.user.aggregate.event.UserAuthFailedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregateAuthFailTest {

    private FixtureConfiguration<UserAggregate> fixture;

    @BeforeEach
    public void setup() {

        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        fixture = new AggregateTestFixture<>(UserAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(passwordEncoder);
    }

    @Test
    public void whenCommandIsValidThenExpectEvent() {

        UUID id = UUID.randomUUID();

        UserAuthFailCommand command = new UserAuthFailCommand(id);

        UserAuthFailedEvent event = new UserAuthFailedEvent(id);

        fixture.givenCommands(createUserCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserAuthFailCommand command = new UserAuthFailCommand(null);

        fixture.givenCommands(createUserCommand(UUID.randomUUID()))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private UserCreateRegularCommand createUserCommand(UUID id) {

        return UserCreateRegularCommand.builder()
                .id(id)
                .email("test@test")
                .password("password")
                .build();
    }
}
