package com.example.demo.user.aggregate;

import com.example.demo.user.aggregate.command.UserAuthSuccessCommand;
import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregateAuthSuccessTest {

    private FixtureConfiguration<UserAggregate> fixture;

    @BeforeEach
    public void setup() {

        PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("password");

        fixture = new AggregateTestFixture<>(UserAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(encoder);
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        UUID id = UUID.randomUUID();

        UserAuthSuccessCommand command = new UserAuthSuccessCommand(id);

        fixture.givenCommands(createRegularCommand(id))
            .when(command)
            .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserAuthSuccessCommand command = new UserAuthSuccessCommand(null);

        fixture.givenCommands(createRegularCommand(UUID.randomUUID()))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    private UserCreateRegularCommand createRegularCommand(UUID id) {

        return UserCreateRegularCommand.builder()
                .id(id)
                .email("test@tes")
                .password("password")
                .build();
    }
}
