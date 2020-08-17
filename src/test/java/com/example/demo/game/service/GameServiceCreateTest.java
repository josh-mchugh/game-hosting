package com.example.demo.game.service;

import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.sample.TestGameUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServiceCreateTest {

    @Autowired
    private IGameService gameService;

    @Test
    public void whenNoEntitiesThenExistsAllReturnFalse() {

        boolean exists = gameService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenEntitiesThenExistsAllReturnTrue() {

        GameCreateRequest gameCreateRequest = TestGameUtil.builder().build();
        gameService.handleGameCreateRequest(gameCreateRequest);

        boolean exists = gameService.existsAny();

        Assertions.assertTrue(exists);
    }
}
