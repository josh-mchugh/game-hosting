package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.service.IFlavorService;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
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
public class FlavorProjectorExistsAnyTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IFlavorProjector flavorProjectionService;

    @Autowired
    private IFlavorService flavorService;

    private Region region;

    @BeforeEach
    public void setup() {

        region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();
    }

    @Test
    public void testExistsAllShouldBeFalse() {

        boolean exists = flavorProjectionService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistsAllShouldBeTrue() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .flavorId("d23f7fd6-a250-4600-bb95-bb4cd12d9a01")
                .regionId(region.getId())
                .name("s1-8")
                .type("ovh.vps-ssd")
                .available(true)
                .hourly("s1-8.consumption")
                .monthly("s1-8.monthly")
                .quota(3)
                .osType("linux")
                .vcpus(2)
                .ram(8000)
                .disk(40)
                .outboundBandwidth(100)
                .inboundBandwidth(100)
                .build();

        flavorService.handleCreated(event);

        boolean exists = flavorProjectionService.existsAny();

        Assertions.assertTrue(exists);
    }

}
