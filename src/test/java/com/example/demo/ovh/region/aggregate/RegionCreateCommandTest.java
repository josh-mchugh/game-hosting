package com.example.demo.ovh.region.aggregate;

import com.example.demo.ovh.region.aggregate.command.RegionCreateCommand;
import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionCreateCommandTest {

    private FixtureConfiguration<RegionAggregate> fixture;

    @BeforeEach
    public void setup() {

        fixture = new AggregateTestFixture<>(RegionAggregate.class)
                .registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void whenCreateIsValidThenExecuteCreatedEvent() {

        UUID id = UUID.randomUUID();

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(id)
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateHasNullIdThenThrowException() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(null)
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullNameThenThrowException() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name(null)
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasBlankNameThenThrowException() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(JSR303ViolationException.class);
    }

    @Test
    public void whenCreateHasNullContinentCodeThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode(null)
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankContinentCodeThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullCountryCodesThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("continent code")
                .countryCodes(null)
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankCountryCodesThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("continent code")
                .countryCodes("")
                .dataCenterLocation("datacenter")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullDatacenterLocationThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation(null)
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasBlankDatacenterLocationThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("")
                .status(RegionStatus.UP)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }

    @Test
    public void whenCreateHasNullStatusThenExecuteSuccessfully() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.randomUUID())
                .name("name")
                .continentCode("continent code")
                .countryCodes("country codes")
                .dataCenterLocation("datacenter")
                .status(null)
                .build();

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution();
    }
}
