package com.example.demo.ovh.region.aggregate.command;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionUpdateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasContentCodeThenReturnContentCode() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .continentCode("continentCode")
                .build();

        Assertions.assertEquals("continentCode", command.getContinentCode());
    }

    @Test
    public void whenCommandHasCountryCodesThenReturnCountryCodes() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .countryCodes("countryCodes")
                .build();

        Assertions.assertEquals("countryCodes", command.getCountryCodes());
    }

    @Test
    public void whenCommandHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals("dataCenterLocation", command.getDataCenterLocation());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(RegionStatus.UP, command.getStatus());
    }

    @Test
    public void whenCommandToString() {

        RegionUpdateCommand command = command();

        String expected = "RegionUpdateCommand(id=aee9a6ff-6222-439c-999f-2c5a5cc383c2, continentCode=continentCode, countryCodes=countryCodes, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        RegionUpdateCommand command = RegionUpdateCommand.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals(-846784272, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        RegionUpdateCommand command1 = command();
        RegionUpdateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        RegionUpdateCommand command = command();

        Assertions.assertNotEquals(command, RegionUpdateCommand.builder().build());
    }

    private RegionUpdateCommand command() {

        return RegionUpdateCommand.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .status(RegionStatus.UP)
                .build();
    }
}
