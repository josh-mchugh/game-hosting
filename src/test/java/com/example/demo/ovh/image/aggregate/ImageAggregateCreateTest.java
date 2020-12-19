package com.example.demo.ovh.image.aggregate;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.event.ImageCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageAggregateCreateTest {

    private FixtureConfiguration<ImageAggregate> fixture;

    @BeforeEach
    public void setup() {

        this.fixture = new AggregateTestFixture<>(ImageAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateIsValidThenExpectEvent() {

        UUID id = UUID.randomUUID();
        LocalDateTime createdDate = LocalDateTime.now();

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(id)
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .imageCreatedDate(createdDate)
                .flavorType("flavor-type")
                .hourly("hourly")
                .monthly("monthly")
                .size(1.0D)
                .minRam(1)
                .minDisk(1)
                .username("username")
                .status("status")
                .build();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(id)
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .type("type")
                .imageCreatedDate(createdDate)
                .flavorType("flavor-type")
                .hourly("hourly")
                .monthly("monthly")
                .size(1.0D)
                .minRam(1)
                .minDisk(1)
                .username("username")
                .status("status")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasMinThenExpectEvent() {

        UUID id = UUID.randomUUID();

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(id)
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .build();

        ImageCreatedEvent event = ImageCreatedEvent.builder()
                .id(id)
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasNullIdThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullRegionIdThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankRegionThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullOvhIdThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankOvhIdThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankNameThenThrowException() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullTypeThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .type(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankTypeThenExpectSuccessful()  {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .type("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullImageCreatedDateThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .imageCreatedDate(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullFlavorTypeThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .flavorType(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankFlavorTypeThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .flavorType("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullHourlyThenExpectedSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .hourly(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankHourlyThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .hourly("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullMonthlyThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .monthly(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankMonthlyThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .monthly("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullSizeThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .size(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullMinRamThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .minRam(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullMinDiskThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .minDisk(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullUserNameThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .username(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankUserNameThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .username("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullStatusThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .status(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankStatusThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .status("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullVisibilityThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .visibility(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankVisibilityThenExpectSuccessful() {

        ImageCreateCommand command = ImageCreateCommand.builder()
                .id(UUID.randomUUID())
                .regionId("region-id")
                .ovhId("ovhId")
                .name("name")
                .visibility("")
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
