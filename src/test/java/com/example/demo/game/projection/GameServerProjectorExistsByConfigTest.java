package com.example.demo.game.projection;

import com.example.demo.game.projection.model.ExistsGameServerByConfigQuery;
import com.example.demo.game.projection.model.ExistsGameServerByConfigResponse;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class GameServerProjectorExistsByConfigTest {

    @Autowired
    private IGameServerProjector gameServerProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData sampleData;

    @BeforeEach
    public void setup() {

        sampleData = sampleBuilder.builder()
                .game()
                .region()
                .flavor()
                .image()
                .build();
    }

    @Test
    public void whenQueryIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> gameServerProjector.existsByConfig(null));
    }

    @Test
    public void whenQueryHasNullGameIdThenThrowException() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(null)
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameServerProjector.existsByConfig(query));
    }

    @Test
    public void whenQueryHasNullRegionIdThenThrowException() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(null)
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameServerProjector.existsByConfig(query));
    }

    @Test
    public void whenQueryHasNullFlavorIdThenThrowException() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId(null)
                .imageId(sampleData.getFlavor().getId())
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameServerProjector.existsByConfig(query));
    }

    @Test
    public void whenQueryHasNullImageIdThenThrowException() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> gameServerProjector.existsByConfig(query));
    }

    @Test
    public void whenQueryIsValidAndEntityDoesNotExistsThenReturnFalse() {

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndEntityExistsThenReturnTrue() {

       sampleBuilder.builder()
               .gameServer()
               .build();

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertTrue(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndHasInvalidGameIdThenReturnFalse(){

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId("invalidGameId")
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndHasInvalidRegionIdThenReturnFalse(){

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId("invalidRegionId")
                .flavorId(sampleData.getFlavor().getId())
                .imageId(sampleData.getImage().getId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndHasInvalidFlavorIdThenReturnFalse(){

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId("invalidFlavorId")
                .imageId(sampleData.getImage().getId())
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertFalse(response.isExists());
    }

    @Test
    public void whenQueryIsValidAndHasInvalidImageIdThenReturnFalse(){

        sampleBuilder.builder()
                .gameServer()
                .build();

        ExistsGameServerByConfigQuery query = ExistsGameServerByConfigQuery.builder()
                .gameId(sampleData.getGame().getId())
                .regionId(sampleData.getRegion().getId())
                .flavorId(sampleData.getFlavor().getId())
                .imageId("invalidImageId")
                .build();

        ExistsGameServerByConfigResponse response = gameServerProjector.existsByConfig(query);

        Assertions.assertFalse(response.isExists());
    }
}
