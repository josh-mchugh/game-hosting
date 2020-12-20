package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameProjection;
import com.example.demo.ovh.region.projection.model.FetchRegionIdByNameQuery;
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
public class RegionProjectFetchIdByNameTest {

    @Autowired
    private IRegionProjector regionProjection;

    @Autowired
    private IRegionService regionService;

    @Test
    public void whenParamIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> regionProjection.fetchIdByName(null));
    }

    @Test
    public void whenRegionExistsThenReturnResponse() {

        UUID id = UUID.randomUUID();

        RegionCreatedEvent event = RegionCreatedEvent.builder()
                .id(id)
                .name("name")
                .build();

        regionService.handleCreated(event);

        FetchRegionIdByNameQuery query = new FetchRegionIdByNameQuery("name");
        FetchRegionIdByNameProjection response = regionProjection.fetchIdByName(query);

        FetchRegionIdByNameProjection expectedResponse = new FetchRegionIdByNameProjection(id.toString());

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenRegionDoesNotExistThenReturnNull() {

        FetchRegionIdByNameQuery query = new FetchRegionIdByNameQuery("name");
        FetchRegionIdByNameProjection response = regionProjection.fetchIdByName(query);

        Assertions.assertNull(response);
    }
}
