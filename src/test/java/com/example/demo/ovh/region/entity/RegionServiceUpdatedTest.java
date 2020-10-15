package com.example.demo.ovh.region.entity;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.aggregate.event.RegionUpdatedEvent;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionServiceUpdatedTest {

    @Autowired
    private IRegionService regionService;

    private Region region;

    @BeforeEach
    private void setup() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        region = regionService.handleCreated(event);
    }

    @Test
    public void whenUpdatedIsValidThenReturnExpected() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Region expectedRegion = Region.builder()
                .id(region.getId())
                .name("US-EAST-VA-1")
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Assertions.assertEquals(expectedRegion, updatedRegion);
    }

    @Test
    public void whenUpdateHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> regionService.handleCreated(null));
    }

    @Test
    public void whenUpdatedHasIdThenReturnId() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertEquals(region.getId(), updatedRegion.getId());
    }

    @Test
    public void whenUpdatedHasNullIdThenThrowException() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(null)
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> regionService.handleUpdated(event));
    }

    @Test
    public void whenUpdatedHasContinentCodeThenReturnContinentCode() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertEquals("EU", updatedRegion.getContinentCode());
    }

    @Test
    public void whenUpdatedHasNullContinentCodeThenReturnNull() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode(null)
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertNull(updatedRegion.getContinentCode());
    }

    @Test
    public void whenUpdatedHasCountryCodesThenReturnCountryCodeS() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertEquals("uk", updatedRegion.getCountryCodes());
    }

    @Test
    public void whenUpdatedHasNullCountryCodesThenReturnNull() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes(null)
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertNull(updatedRegion.getCountryCodes());
    }

    @Test
    public void whenUpdatedHasDatacenterLocationThenReturnDatacenterLocation() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertEquals("uk", updatedRegion.getDataCenterLocation());
    }

    @Test
    public void whenUpdatedHasNullDatacenterLocationThenReturnNull() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation(null)
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertNull(updatedRegion.getDataCenterLocation());
    }

    @Test
    public void whenUpdatedHasStatusThenReturnStatus() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(RegionStatus.DOWN)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertEquals(RegionStatus.DOWN, updatedRegion.getStatus());
    }

    @Test
    public void whenUpdatedHasNullStatusThenReturnNull() {

        RegionUpdatedEvent event = RegionUpdatedEvent.builder()
                .id(UUID.fromString(region.getId()))
                .continentCode("EU")
                .countryCodes("uk")
                .dataCenterLocation("uk")
                .status(null)
                .build();

        Region updatedRegion = regionService.handleUpdated(event);

        Assertions.assertNull(updatedRegion.getStatus());
    }
}
