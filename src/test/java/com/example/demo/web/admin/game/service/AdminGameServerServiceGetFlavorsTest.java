package com.example.demo.web.admin.game.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerFlavorsResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AdminGameServerServiceGetFlavorsTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private AdminGameServerService service;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getFlavors(null));
    }

    @Test
    public void whenQueryHasNullRegionIdThenThrowException() {

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("", null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getFlavors(query));
    }

    @Test
    public void whenQueryHasNullSearchThenExpectResult() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery(null, sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = service.getFlavors(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getFlavors()));
    }

    @Test
    public void whenQueryHasSearchWithNoMatchingThenExpectNoResults() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("4", sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = service.getFlavors(query);

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getFlavors()));
    }

    @Test
    public void whenQueryHasSearchWithMatchingThenExpectNoResults() {

        SampleData sampleData = sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        FetchAdminGameServerFlavorsQuery query = new FetchAdminGameServerFlavorsQuery("2", sampleData.getRegion().getId().toString());
        FetchAdminGameServerFlavorsResponse response = service.getFlavors(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getFlavors()));
    }
}
