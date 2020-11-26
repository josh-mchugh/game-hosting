package com.example.demo.user.aggregate;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserAggregateRecoveryTokenCreateCommandTest {

    private FixtureConfiguration<UserAggregate> fixture;

    @BeforeEach
    public void setup() {

        PasswordEncoder encoder = Mockito.mock(PasswordEncoder.class);
        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("");

        AppConfig.Password password = new AppConfig.Password();
        password.setRecoveryExpirationOffset(100L);

        AppConfig appConfig = new AppConfig();
        appConfig.setPassword(password);

        fixture = new AggregateTestFixture<>(UserAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>())
                .registerInjectableResource(encoder)
                .registerInjectableResource(appConfig);
    }

    @Test
    public void whenCommandIsValidThenExpectSuccess() {

        UUID id = UUID.randomUUID();

        UserRecoveryTokenCreateCommand command = new UserRecoveryTokenCreateCommand(id);

        fixture.givenCommands(createRegularCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCommandHasNullIdThenExpectException() {

        UserRecoveryTokenCreateCommand command = new UserRecoveryTokenCreateCommand(null);

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
