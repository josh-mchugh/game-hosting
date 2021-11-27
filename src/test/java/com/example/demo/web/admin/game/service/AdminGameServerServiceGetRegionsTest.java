package com.example.demo.web.admin.game.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerRegionsResponse;
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
public class AdminGameServerServiceGetRegionsTest {

    @Autowired
    private AdminGameServerService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullAndEntityExistsThenExpectResults() {

        sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerRegionsResponse response = service.getRegions(null);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getRegions()));
    }

    @Test
    public void whenRegionsExistsThenExpectResults() {

        sampleBuilder.builder()
                .region()
                .build();

        FetchAdminGameServerRegionsResponse response = service.getRegions(new FetchAdminGameServerRegionsQuery());

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getRegions()));
    }

    @Test
    public void whenRegionsDoNotExistsThenExpectEmptyResults() {

        FetchAdminGameServerRegionsResponse response = service.getRegions(new FetchAdminGameServerRegionsQuery());

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getRegions()));
    }
}
