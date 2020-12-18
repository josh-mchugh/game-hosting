package com.example.demo.ovh.flavor.aggregate;

import com.example.demo.ovh.flavor.aggregate.command.FlavorCreateCommand;
import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FlavorCreateCommandTest {

    private FixtureConfiguration<FlavorAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(FlavorAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateCommandIsValidThenExpectEvents() {

        UUID id = UUID.randomUUID();

        FlavorCreateCommand command = createFlavorCommand(id).build();

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
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

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateCommandHasNullIdThenThrowException() {

        FlavorCreateCommand command = createFlavorCommand()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullRegionIdThenThrowException() {

        FlavorCreateCommand command = createFlavorCommand()
                .regionId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankRegionIdThenThrowException() {

        FlavorCreateCommand command = createFlavorCommand()
                .regionId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullOVhIdThenThrowException() {

        FlavorCreateCommand command = createFlavorCommand()
                .ovhId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasBlankOvhIdThenThrowException() {

        FlavorCreateCommand command = createFlavorCommand()
                .ovhId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateCommandHasNullNameThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .name(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankNameThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .name("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullTypeThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .type(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankTypeThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .type("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullAvailableThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .available(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullHourlyThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .hourly(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankHourlyThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .hourly("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullMonthlyThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .monthly(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankMonthlyThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .hourly("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullQuotaThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .quota(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullOsTypeThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .osType(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasBlankOsTypeThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .osType("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullVcpusThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .vcpus(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullRamThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .ram(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullDiskThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .disk(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullInboundBandwidthThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .inboundBandwidth(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateCommandHasNullOutboundBandwidthThenExpectSuccessful() {

        FlavorCreateCommand command = createFlavorCommand()
                .outboundBandwidth(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    private FlavorCreateCommand.Builder createFlavorCommand() {

        return createFlavorCommand(UUID.randomUUID());
    }

    private FlavorCreateCommand.Builder createFlavorCommand(UUID id) {

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
                .outboundBandwidth(1);
    }
}
