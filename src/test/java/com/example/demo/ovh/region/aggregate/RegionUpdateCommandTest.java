package com.example.demo.ovh.region.aggregate;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.command.RegionUpdateCommand;
import com.example.demo.ovh.region.aggregate.event.RegionUpdatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionUpdateCommandTest {

    private FixtureConfiguration<RegionAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(RegionAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenUpdateIsValidThenExecuteUpdatedEvent() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenUpdateHasNullIdThenThrowException() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(null)
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenUpdateHasNullContinentCodeThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode(null)
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankContinentCodeThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullCountryCodesThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes(null)
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankCountryCodesThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("")
                .dataCenterLocation("datacenter location")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullDatacenterLocationThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation(null)
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasBlankDatacenterLocationThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("")
                .status(RegionStatus.DOWN)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenUpdateHasNullStatusThenExecuteSuccessfully() {

        RegionCreateCommand createCommand = createCommand();

        RegionUpdateCommand updateCommand = RegionUpdateCommand.builder()
                .id(createCommand.getId())
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter location")
                .status(null)
                .build();

        fixture.givenCommands(createCommand)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution();
    }

    private RegionCreateCommand createCommand() {

        return RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .build();
    }
}
