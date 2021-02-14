package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.ovh.region.projection.model.FetchRegionByNameQuery;
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
public class RegionProjectFetchRegionByNameTest {

    @Autowired
    private IRegionProjector regionProjection;

    @Autowired
    private IRegionService regionService;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> regionProjection.fetchRegionByName(null));
    }

    @Test
    public void whenRegionExistsThenReturnResponse() {

        UUID id = UUID.randomUUID();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .name("name")
                .build();

        regionService.handleCreated(event);

        FetchRegionByNameQuery query = new FetchRegionByNameQuery("name");
        Region response = regionProjection.fetchRegionByName(query);

        Assertions.assertEquals(id, response.getId());
    }

    @Test
    public void whenRegionDoesNotExistThenReturnNull() {

        FetchRegionByNameQuery query = new FetchRegionByNameQuery("name");
        Region response = regionProjection.fetchRegionByName(query);

        Assertions.assertNull(response);
    }
}
