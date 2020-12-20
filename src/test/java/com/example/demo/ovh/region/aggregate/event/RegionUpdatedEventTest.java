package com.example.demo.ovh.region.aggregate.event;

import com.example.demo.ovh.region.entity.RegionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RegionUpdatedEventTest {

    @Test
    public void whenEventHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(id)
                .build();

        Assertions.assertEquals(id, event.getId());
    }

    @Test
    public void whenEventHasContentCodeThenReturnContentCode() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .continentCode("continentCode")
                .build();

        Assertions.assertEquals("continentCode", event.getContinentCode());
    }

    @Test
    public void whenEventHasCountryCodesThenReturnCountryCodes() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .countryCodes("countryCodes")
                .build();

        Assertions.assertEquals("countryCodes", event.getCountryCodes());
    }

    @Test
    public void whenEventHasDataCenterLocationThenReturnDataCenterLocation() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals("dataCenterLocation", event.getDataCenterLocation());
    }

    @Test
    public void whenEventHasStatusThenReturnStatus() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(RegionStatus.UP, event.getStatus());
    }

    @Test
    public void whenEventToString() {

        RegionUpdatedEvent event = event();

        String expected = "RegionUpdatedEvent(id=aee9a6ff-6222-439c-999f-2c5a5cc383c2, continentCode=continentCode, countryCodes=countryCodes, dataCenterLocation=dataCenterLocation, status=UP)";

        Assertions.assertEquals(expected, event.toString());
    }

    @Test
    public void whenEventHashCode() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .build();

        Assertions.assertEquals(-846784272, event.hashCode());
    }

    @Test
    public void whenEventEquals() {

        RegionUpdatedEvent event1 = event();
        RegionUpdatedEvent event2 = event();

        Assertions.assertEquals(event1, event2);
    }

    @Test
    public void whenEventNotEquals() {

        RegionUpdatedEvent event = event();

        Assertions.assertNotEquals(event, RegionUpdatedEvent.builder().build());
    }

    private RegionUpdatedEvent event() {

        return RegionUpdatedEvent.builder()
                .id(UUID.fromString("aee9a6ff-6222-439c-999f-2c5a5cc383c2"))
                .continentCode("continentCode")
                .countryCodes("countryCodes")
                .dataCenterLocation("dataCenterLocation")
                .status(RegionStatus.UP)
                .build();
    }
}
