package com.example.demo.ovh.flavor.scheduler.projection;

import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameQuery;
import com.example.demo.ovh.flavor.scheduler.projection.model.FetchRegionIdsGroupedByNameResponse;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSchedulerProjectionServiceFetchRegionIdsGroupedByNameTest {

    @Autowired
    private FlavorSchedulerProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.fetchRegionIdsGroupedByName(null));
    }

    @Test
    public void whenRegionExistsThenExpectRegionNameInKeySet() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = service.fetchRegionIdsGroupedByName(query);

        Assertions.assertTrue(response.getRegions().containsKey(region.getName()));
    }

    @Test
    public void whenRegionExistsThenExpectRegionIdAsValueOfKey() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = service.fetchRegionIdsGroupedByName(query);

        Assertions.assertEquals(region.getId().toString(), response.getRegions().get(region.getName()));
    }

    @Test
    public void whenRegionsDoesNotExistThenExpectEmptyMap() {

        FetchRegionIdsGroupedByNameQuery query = new FetchRegionIdsGroupedByNameQuery();
        FetchRegionIdsGroupedByNameResponse response = service.fetchRegionIdsGroupedByName(query);

        Assertions.assertEquals(ImmutableMap.of(), response.getRegions());
    }
}
