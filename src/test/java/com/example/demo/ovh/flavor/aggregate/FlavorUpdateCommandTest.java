package com.example.demo.ovh.flavor.aggregate;

import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.command.FlavorUpdateCommand;
import com.example.demo.ovh.flavor.aggregate.event.FlavorUpdatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorUpdateCommandTest {

    private FixtureConfiguration<FlavorAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(FlavorAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenUpdateCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id).build();

        FlavorUpdatedEvent updatedEvent = FlavorUpdatedEvent.builder()
                .id(id)
                .regionId("region-id")
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(updatedEvent);
    }

    @Test
    public void whenUpdateCommandHasNullIdThenThrowException() {

        FlavorCreateCommand createCommand = flavorCreateCommand();

        FlavorUpdateCommand updateCommand = flavorUpdateCommand()
                .id(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateCommandHasNullRegionIdThenThrowException() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .regionId(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateCommandHasBlankRegionIdThenThrowException() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .regionId("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateCommandHasNullNameThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .name(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasBlankNameThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .name("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .type(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasBlankTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .type("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullAvailableThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .available(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullHourlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .hourly(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasBlankHourlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .hourly("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullMonthlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .monthly(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasBlankMonthlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .hourly("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullQuotaThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .quota(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullOsTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .osType(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasBlankOsTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .osType("")
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullVcpusThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .vcpus(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullRamThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .ram(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullDiskThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .disk(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullInboundBandwidthThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .inboundBandwidth(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateCommandHasNullOutboundBandwidthThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand createCommand = flavorCreateCommand(id);

        FlavorUpdateCommand updateCommand = flavorUpdateCommand(id)
                .name(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    private FlavorUpdateCommand.Builder flavorUpdateCommand() {

        return flavorUpdateCommand(UUID.randomUUID());
    }

    private FlavorUpdateCommand.Builder flavorUpdateCommand(UUID id) {

        return FlavorUpdateCommand.builder()
                .id(id)
                .regionId("region-id")
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1);
    }

    private FlavorCreateCommand flavorCreateCommand() {

        return flavorCreateCommand(UUID.randomUUID());
    }

    private FlavorCreateCommand flavorCreateCommand(UUID id) {

        return FlavorCreateCommand.builder()
                .id(id)
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .available(true)
                .hourly("hourly")
                .monthly("monthly")
                .quota(1)
                .osType("osType")
                .vcpus(1)
                .ram(1)
                .disk(1)
                .inboundBandwidth(1)
                .outboundBandwidth(1)
                .build();
    }
}
