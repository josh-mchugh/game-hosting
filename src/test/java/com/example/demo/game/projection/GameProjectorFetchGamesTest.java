package com.example.demo.game.projection;

import com.example.demo.game.projection.model.FetchAdminGameServerGamesQuery;
import com.example.demo.game.projection.model.FetchAdminGameServerGamesResponse;
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
public class GameProjectorFetchGamesTest {

    @Autowired
    private IGameProjector gameProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenExpectResults() {

        sampleBuilder.builder()
                .game()
                .build();

        FetchAdminGameServerGamesResponse response = gameProjector.fetchGames(null);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getGames()));
    }
    
    @Test
    public void whenQueryIsNotNullThenExpectResults() {

        sampleBuilder.builder()
                .game()
                .build();

        FetchAdminGameServerGamesResponse response = gameProjector.fetchGames(new FetchAdminGameServerGamesQuery());

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getGames()));
    }
}
