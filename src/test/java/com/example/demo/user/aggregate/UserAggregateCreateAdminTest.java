package com.example.demo.user.aggregate;

import com.example.demo.user.aggregate.command.UserCreateAdminCommand;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregateCreateAdminTest {

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

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullEmailThenExpectException() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankEmailThenExpectException() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasNullPasswordThenExpectException() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCommandHasBlankPasswordThenExpectException() {

        UserCreateAdminCommand command = UserCreateAdminCommand.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }
}
