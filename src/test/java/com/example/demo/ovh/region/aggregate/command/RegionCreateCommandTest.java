package com.example.demo.ovh.region.aggregate.command;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionCreateCommandTest {

    @Test
    public void whenCommandHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, command.getId());
    }

    @Test
    public void whenCommandHasNameThenReturnName() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", command.getName());
    }

    @Test
    public void whenCommandHasContentCodeThenReturnContentCode() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .continentCode("continentCode")
                .build();

        Assertions.assertEquals("continentCode", command.getContinentCode());
    }
    
    @Test
    public void whenCommandHasCountryCodesThenReturnCountryCodes() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .countryCodes("countryCodes")
                .build();

        Assertions.assertEquals("countryCodes", command.getCountryCodes());
    }

    @Test
    public void whenCommandHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals("dataCenterLocation", command.getDataCenterLocation());
    }

    @Test
    public void whenCommandHasStatusThenReturnStatus() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(RegionStatus.UP, command.getStatus());
    }

    @Test
    public void whenCommandToString() {

        RegionCreateCommand command = command();

        String expected = "RegionCreateCommand(id=aee9a6ff-6222-439c-999f-2c5a5cc383c2, name=name, continentCode=continentCode, countryCodes=countryCodes, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, command.toString());
    }

    @Test
    public void whenCommandHashCode() {

        RegionCreateCommand command = RegionCreateCommand.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .name("name")
                .continentCode("continetCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals(550472829, command.hashCode());
    }

    @Test
    public void whenCommandEquals() {

        RegionCreateCommand command1 = command();
        RegionCreateCommand command2 = command();

        Assertions.assertEquals(command1, command2);
    }

    @Test
    public void whenCommandNotEquals() {

        RegionCreateCommand command = command();

        Assertions.assertNotEquals(command, RegionCreateCommand.builder().build());
    }

    private RegionCreateCommand command() {

        return RegionCreateCommand.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .name("name")
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .status(RegionStatus.UP)
                .build();
    }
}
