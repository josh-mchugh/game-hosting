package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
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
public class FlavorProjectorFetchFlavorByOvhIdTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IFlavorProjector flavorProjector;

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
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> flavorProjector.fetchFlavorByOvhId(null));
    }

    @Test
    public void whenFlavorExistsThenReturnFlavorWithMatchingId() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .ovhId("ovhId")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        Flavor response = flavorProjector.fetchFlavorByOvhId("ovhId");

        Assertions.assertEquals(flavor.getId(), response.getId());
    }

    @Test
    public void whenFlavorExistsThenReturnFlavorWithMatchingOvId() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .ovhId("ovhId")
                .build();

        flavorService.handleCreated(event);

        Flavor response = flavorProjector.fetchFlavorByOvhId("ovhId");

        Assertions.assertEquals("ovhId", response.getOvhId());
    }

    @Test
    public void whenFlavorDoesNotExistsThenReturnNull() {

        Assertions.assertNull(flavorProjector.fetchFlavorByOvhId("ovhId"));
    }
}
