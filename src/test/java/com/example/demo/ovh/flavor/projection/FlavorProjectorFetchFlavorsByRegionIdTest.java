package com.example.demo.ovh.flavor.projection;

import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.ovh.flavor.projection.model.FetchAdminGameServerFlavorsResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class FlavorProjectorFetchFlavorsByRegionIdTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IFlavorProjector flavorProjector;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> flavorProjector.fetchFlavorsByRegionId(null));
    }

    @Test
    public void whenQueryHasNullRegionIdThenThrowException() {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("", null);

        Assertions.assertThrows(NullPointerException.class, () -> flavorProjector.fetchFlavorsByRegionId(query));
    }

    @Test
    public void whenQueryHasNullSearchThenExpectResult() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(null, sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = flavorProjector.fetchFlavorsByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getFlavors()));
    }

    @Test
    public void whenQueryHasSearchWithNoMatchingThenExpectNoResults() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("4", sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = flavorProjector.fetchFlavorsByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getFlavors()));
    }

    @Test
    public void whenQueryHasSearchWithMatchingThenExpectNoResults() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("2", sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = flavorProjector.fetchFlavorsByRegionId(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getFlavors()));
    }
}
