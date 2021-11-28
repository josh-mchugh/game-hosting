package com.example.demo.ovh.region.entity.service;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.model.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionServiceCreatedTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void whenCreatedIsValidThenReturnExpected() {

        UUID id = UUID.randomUUID();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Region expectedRegion = Region.builder()
                .id(id)
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Assertions.assertEquals(expectedRegion, region);
    }

    @Test
    public void whenCreatedParamIsNullThenException() {

        Assertions.assertThrows(NullPointerException.class, () -> regionService.handleCreated(null));
    }

    @Test
    public void whenCreatedHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals(id, region.getId());
    }

    @Test
    public void whenCreatedHasNullIdThenThrowException() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(null)
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> regionService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasNameThenReturnName() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals("US-EAST-VA-1", region.getName());
    }

    @Test
    public void whenCreatedHasNullNameThenThrowException() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name(null)
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> regionService.handleCreated(event));
    }

    @Test
    public void whenCreatedHasContinentCodesThenReturnContentCodes() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals("US", region.getContinentCode());
    }

    @Test
    public void whenCreatedHasContinentCodesThenReturnNull() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode(null)
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertNull(region.getContinentCode());
    }

    @Test
    public void whenCreatedHasCountryCodesThenReturnCountryCodes() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals("us", region.getCountryCodes());
    }

    @Test
    public void whenCreatedHasNullCountryCodesThenReturnNull() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes(null)
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertNull(region.getCountryCodes());
    }

    @Test
    public void whenCreatedHasDatacenterLocationThenReturnDatacenterLocation() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals("US-EAST-VA", region.getDataCenterLocation());
    }

    @Test
    public void whenCreatedHasNullDatacenterLocationThenReturnNull() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation(null)
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertNull(region.getDataCenterLocation());
    }

    @Test
    public void whenCreatedHasStatusThenReturnStatus() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertEquals(RegionStatus.UP, region.getStatus());
    }

    @Test
    public void whenCreatedHasNullStatusThenReturnNull() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(null)
                .build();

        Region region = regionService.handleCreated(event);

        Assertions.assertNull(region.getStatus());
    }
}
