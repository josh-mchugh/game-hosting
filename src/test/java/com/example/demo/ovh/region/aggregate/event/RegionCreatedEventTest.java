package com.example.demo.ovh.region.aggregate.event;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionCreatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasNameThenReturnName() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .name("name")
                .build();

        Assertions.assertEquals("name", event.getName());
    }

    @Test
    public void whenEventHasContentCodeThenReturnContentCode() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .continentCode("continentCode")
                .build();

        Assertions.assertEquals("continentCode", event.getContinentCode());
    }

    @Test
    public void whenEventHasCountryCodesThenReturnCountryCodes() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .countryCodes("countryCodes")
                .build();

        Assertions.assertEquals("countryCodes", event.getCountryCodes());
    }

    @Test
    public void whenEventHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals("dataCenterLocation", event.getDataCenterLocation());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(RegionStatus.UP, event.getStatus());
    }

    @Test
    public void whenEventToString() {

        RegionCreatedEvent event = event();

        String expected = "RegionCreatedEvent(id=aee9a6ff-6222-439c-999f-2c5a5cc383c2, name=name, continentCode=continentCode, countryCodes=countryCodes, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .name("name")
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals(2003417031, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        RegionCreatedEvent event1 = event();
        RegionCreatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        RegionCreatedEvent event = event();

        Assertions.assertNotEquals(event, RegionCreatedEvent.builder().build());
    }

    private RegionCreatedEvent event() {

        return RegionCreatedEvent.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .name("name")
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .status(RegionStatus.UP)
                .build();
    }
}
