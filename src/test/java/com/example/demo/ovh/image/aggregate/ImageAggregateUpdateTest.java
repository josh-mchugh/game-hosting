package com.example.demo.ovh.image.aggregate;

import com.example.demo.ovh.image.aggregate.command.ImageCreateCommand;
import com.example.demo.ovh.image.aggregate.command.ImageUpdateCommand;
import com.example.demo.ovh.image.aggregate.event.ImageUpdatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class ImageAggregateUpdateTest {

    private FixtureConfiguration<ImageAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(ImageAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenUpdateIsValidThenExecuteEvent() {

        UUID id = UUID.randomUUID();
        LocalDateTime createdDate = LocalDateTime.now();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
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
                .visibility("visibility")
                .build();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
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
                .visibility("visibility")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenUpdateIsMinValidThenExecuteEvent() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .build();

        ImageUpdatedEvent event = ImageUpdatedEvent.builder()
                .id(id)
                .ovhId("ovhId")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenUpdateHasNullIdThenThrowException() {

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(null)
                .build();

        fixture.givenCommands(createCommand(UUID.randomUUID()))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateHasNullOvhIdThenThrowException() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateHasBlankOvhIdThenThrowException() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateHasNullTypeThemExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .type(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .type("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullImageCreatedDateThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .imageCreatedDate(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullFlavorTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .flavorType(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankFlavorTypeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .flavorType("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullHourlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .hourly(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankHourlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .hourly("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullMonthlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .monthly(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankMonthlyThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .monthly("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullSizeThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .size(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullMinRamThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .minRam(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdatedHasNullMinDiskThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .minDisk(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullUsernameThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .username(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankUsernameThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .username("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullStatusThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .status(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankStatusThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .status("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullVisibilityThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .visibility(null)
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankVisibilityThenExpectSuccessful() {

        UUID id = UUID.randomUUID();

        ImageUpdateCommand command = ImageUpdateCommand.builder()
                .id(id)
                .ovhId("ovhId")
                .visibility("")
                .build();

        fixture.givenCommands(createCommand(id))
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    private ImageCreateCommand createCommand(UUID id) {

        return ImageCreateCommand.builder()
                .id(id)
                .regionId("regionId")
                .ovhId("ovhId")
                .name("name")
                .build();
    }
}
