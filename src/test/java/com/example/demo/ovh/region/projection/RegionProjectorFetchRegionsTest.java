package com.example.demo.ovh.region.projection;

import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.ovh.region.projection.model.FetchAdminGameServerRegionsResponse;
import com.example.demo.sample.SampleBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RegionProjectorFetchRegionsTest {

    @Autowired
    private IRegionProjector regionProjection;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullAndEntityExistsThenExpectResults() {

        sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerRegionsResponse response = regionProjection.fetchRegions(null);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getRegions()));
    }

    @Test
    public void whenRegionsExistsThenExpectResults() {

        sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerRegionsResponse response = regionProjection.fetchRegions(new FetchAdminGameServerRegionsQuery());

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getRegions()));
    }

    @Test
    public void whenRegionsDoNotExistsThenExpectEmptyResults() {

        FetchAdminGameServerRegionsResponse response = regionProjection.fetchRegions(new FetchAdminGameServerRegionsQuery());

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getRegions()));
    }
}
