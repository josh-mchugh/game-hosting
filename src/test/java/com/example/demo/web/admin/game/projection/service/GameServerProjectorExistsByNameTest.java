package com.example.demo.web.admin.game.projection.service;

import com.example.demo.web.admin.game.projection.service.model.ExistsGameServerByNameQuery;
import com.example.demo.web.admin.game.projection.service.model.ExistsGameServerByNameResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class GameServerProjectorExistsByNameTest {

    @Autowired
    private IAdminGameServerProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData sampleData;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByName(null));
    }

    @Test
    public void whenQueryHasNullNameThenThrowException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByName(new ExistsGameServerByNameQuery(null)));
    }

    @Test
    public void whenQueryIsValidAndEntityNotExistsThenReturnFalse() {

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("name");
        ExistsGameServerByNameResponse response = service.existsByName(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndEntityExistsThenReturnTrue() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("name");
        ExistsGameServerByNameResponse response = service.existsByName(query);

        Assertions.assertTrue(response.isExists());
    }

    @Test
    public void whenQueryHasInvalidNameThenReturnFalse() {

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByNameQuery query = new ExistsGameServerByNameQuery("invalidName");
        ExistsGameServerByNameResponse response = service.existsByName(query);

        Assertions.assertFalse(response.isExists());
    }
}
