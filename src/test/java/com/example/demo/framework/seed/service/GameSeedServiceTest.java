package com.example.demo.framework.seed.service;

import com.example.demo.game.entity.GameType;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameSeedServiceTest {

    @Autowired
    private GameSeedService gameSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenGameExistsThenDoesNotExistsReturnFalse() {

        sampleBuilder.builder()
                .game()
                .build();

        Assertions.assertFalse(gameSeedService.dataNotExists());
    }

    @Test
    public void whenGameExistsThenDoesNotExistReturnsTrue() {

        Assertions.assertTrue(gameSeedService.dataNotExists());
    }

    @Test
    public void whenGameIsInitializedThenReturnList() {

        ImmutableList<Object> games = gameSeedService.initializeData();

        Assertions.assertEquals(GameType.values().length, games.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Game", gameSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(gameSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(5, gameSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(gameSeedService.order());
    }
}
