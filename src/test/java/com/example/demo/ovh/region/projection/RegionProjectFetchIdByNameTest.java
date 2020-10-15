package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.aggregate.event.RegionCreatedEvent;
import com.example.demo.ovh.region.entity.service.IRegionService;
import com.example.demo.ovh.region.projection.model.FetchIdByNameQuery;
import com.example.demo.ovh.region.projection.model.FetchIdByNameResponse;
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
    private IRegionProjection regionProjection;

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

        FetchIdByNameQuery query = FetchIdByNameQuery.builder()
                .name("name")
                .build();

        FetchIdByNameResponse response = regionProjection.fetchIdByName(query);

        FetchIdByNameResponse expectedResponse = new FetchIdByNameResponse(id.toString());

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenRegionDoesNotExistThenReturnNull() {

        FetchIdByNameQuery query = FetchIdByNameQuery.builder()
                .name("name")
                .build();

        FetchIdByNameResponse response = regionProjection.fetchIdByName(query);

        Assertions.assertNull(response);
    }
}
