package com.example.demo.game.projection;

import com.example.demo.game.projection.model.ExistsGameServerByNameQuery;
import com.example.demo.game.projection.model.ExistsGameServerByNameResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServerProjectorExistsByNameTest {

    @Autowired
    private IGameServerProjector gameServerProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData sampleData;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> gameServerProjector.existsByName(null));
    }

    @Test
    public void whenQueryHasNullNameThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameServerProjector.existsByName(new ExistsGameServerByNameQuery(null)));
    }

    @Test
    public void whenQueryIsValidAndEntityNotExistsThenReturnFalse() {

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("name");
        ExistsGameServerByNameResponse response = gameServerProjector.existsByName(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndEntityExistsThenReturnTrue() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("name");
        ExistsGameServerByNameResponse response = gameServerProjector.existsByName(query);

        Assertions.assertTrue(response.isExists());
    }

    @Test
    public void whenQueryHasInvalidNameThenReturnFalse() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("invalidName");
        ExistsGameServerByNameResponse response = gameServerProjector.existsByName(query);

        Assertions.assertFalse(response.isExists());
    }
}
