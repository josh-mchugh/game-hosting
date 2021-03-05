package com.example.demo.framework.seed.game.projection;

import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesQuery;
import com.example.demo.framework.seed.game.projection.model.ExistsAnyGamesResponse;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameSeedProjectionServiceExistsAnyTest {

    @Autowired
    private IGameSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenGameDoesNotExistsThenExpectFalse() {

        ExistsAnyGamesResponse response = service.existsAny(new ExistsAnyGamesQuery());

        Assertions.assertFalse(response.exists());
    }

    @Test
    public void whenGameExistsThenExpectTrue() {

        sampleBuilder.builder()
                .game()
                .build();

        ExistsAnyGamesResponse response = service.existsAny(new ExistsAnyGamesQuery());

        Assertions.assertTrue(response.exists());
    }
}
