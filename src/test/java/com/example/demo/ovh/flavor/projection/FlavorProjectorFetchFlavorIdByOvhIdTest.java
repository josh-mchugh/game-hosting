package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.aggregate.event.FlavorCreatedEvent;
import com.example.demo.ovh.flavor.entity.model.Flavor;
import com.example.demo.ovh.flavor.entity.service.IFlavorService;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdProjection;
import com.example.demo.ovh.flavor.projection.model.FetchFlavorIdByOvhIdQuery;
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
public class FlavorProjectorFetchFlavorIdByOvhIdTest {

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
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> flavorProjectionService.fetchFlavorIdByOvhId(null));
    }


    @Test
    public void whenOvhIdIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> flavorProjectionService.fetchFlavorIdByOvhId(new FetchFlavorIdByOvhIdQuery(null)));
    }

    @Test
    public void whenOvhIdIsValidThenReturnId() {

        FlavorCreatedEvent event = FlavorCreatedEvent.builder()
                .id(UUID.randomUUID())
                .regionId(region.getId())
                .ovhId("ovhId")
                .build();

        Flavor flavor = flavorService.handleCreated(event);

        FetchFlavorIdByOvhIdQuery query = new FetchFlavorIdByOvhIdQuery(flavor.getOvhId());
        FetchFlavorIdByOvhIdProjection projection = flavorProjectionService.fetchFlavorIdByOvhId(query);

        Assertions.assertEquals(flavor.getId(), projection.getId());
    }

    @Test
    public void whenOvhIdIsInvalidThenReturnNull() {

        FetchFlavorIdByOvhIdQuery query = new FetchFlavorIdByOvhIdQuery("invalidId");
        FetchFlavorIdByOvhIdProjection projection = flavorProjectionService.fetchFlavorIdByOvhId(query);

        Assertions.assertNull(projection);
    }
}
