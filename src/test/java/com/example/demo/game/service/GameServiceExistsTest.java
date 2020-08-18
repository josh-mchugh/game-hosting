package com.example.demo.game.service;

import com.example.demo.game.service.model.GameCreateRequest;
import com.example.demo.sample.util.TestGameCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServiceExistsTest {

    @Autowired
    private IGameService gameService;

    @Test
    public void whenNoEntitiesThenExistsAllReturnFalse() {

        boolean exists = gameService.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenEntitiesThenExistsAllReturnTrue() {

        GameCreateRequest gameCreateRequest = TestGameCreateRequest.builder().build();
        gameService.handleGameCreateRequest(gameCreateRequest);

        boolean exists = gameService.existsAny();

        Assertions.assertTrue(exists);
    }
}
