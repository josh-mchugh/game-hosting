package com.example.demo.web.admin.game.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesQuery;
import com.example.demo.web.admin.game.service.model.FetchAdminGameServerGamesResponse;
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
public class AdminGameServerServiceGetGamesTest {

    @Autowired
    private AdminGameServerService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.getGames(null));
    }

    @Test
    public void whenParamIsValidAndHasEntitiesThenReturnList() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        FetchAdminGameServerGamesResponse response = service.getGames(new FetchAdminGameServerGamesQuery());

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getGames()));
    }

    @Test
    public void whenParamIsValidAndHasNoEntitiesThenReturnEmptyList() {

        FetchAdminGameServerGamesResponse response = service.getGames(new FetchAdminGameServerGamesQuery());

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getGames()));
    }
}
