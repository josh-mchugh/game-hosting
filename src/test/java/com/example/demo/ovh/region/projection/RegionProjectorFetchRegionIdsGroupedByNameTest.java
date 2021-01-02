package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.ovh.region.projection.model.FetchRegionIdsGroupByNameProjection;
import com.example.demo.sample.SampleBuilder;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionProjectorFetchRegionIdsGroupedByNameTest {

    @Autowired
    private IRegionProjector regionProjection;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenProjectionIsValidThenExpectNameAsKey() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FetchRegionIdsGroupByNameProjection projection = regionProjection.fetchRegionIdsGroupedByName();

        Assertions.assertTrue(projection.getRegionMap().containsKey(region.getName()));
    }

    @Test
    public void whenProjectIsValidThenExpectValueAsId() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FetchRegionIdsGroupByNameProjection projection = regionProjection.fetchRegionIdsGroupedByName();

        Assertions.assertEquals(region.getId(), projection.getRegionMap().get(region.getName()));
    }

    @Test
    public void whenNoRegionsThenExpectEmptyMap() {

        FetchRegionIdsGroupByNameProjection projection = regionProjection.fetchRegionIdsGroupedByName();

        Assertions.assertTrue(MapUtils.isEmpty(projection.getRegionMap()));
    }
}
