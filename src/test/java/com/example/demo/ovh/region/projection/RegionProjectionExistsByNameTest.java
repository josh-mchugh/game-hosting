package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.RegionStatus;
import com.example.demo.ovh.region.entity.service.IRegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionProjectionExistsByNameTest {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IRegionProjector regionProjection;

    @Test
    public void testExistsByNameShouldBeTrue() {

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("US-EAST-VA-1")
                .continentCode("US")
                .countryCodes("us")
                .dataCenterLocation("US-EAST-VA")
                .status(RegionStatus.UP)
                .build();

        regionService.handleCreated(event);

        boolean exists = regionProjection.existsByName("US-EAST-VA-1");

        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByNameShouldByFalse() {

        boolean exists = regionProjection.existsByName("exist-by-name-be-false");

        Assertions.assertFalse(exists);
    }
}
