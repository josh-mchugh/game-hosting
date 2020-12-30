package com.example.demo.project.projection.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchProjectDetailsByIdProjectionTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        FetchProjectDetailsByIdProjection model = new FetchProjectDetailsByIdProjection("name", null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasGameTypeThenReturnGameType() {

        FetchProjectDetailsByIdProjection model = new FetchProjectDetailsByIdProjection(null, GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getGameType());
    }

    @Test
    public void whenModelToString() {

        FetchProjectDetailsByIdProjection model = model();

        String expected = "FetchProjectDetailsByIdProjection(name=name, gameType=MINECRAFT_JAVA)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchProjectDetailsByIdProjection model = new FetchProjectDetailsByIdProjection("name", null);

        Assertions.assertEquals(199052237, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchProjectDetailsByIdProjection model1 = model();
        FetchProjectDetailsByIdProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchProjectDetailsByIdProjection model = model();

        Assertions.assertNotEquals(model, new FetchProjectDetailsByIdProjection("diffName", GameType.MINECRAFT_BEDROCK));
    }

    private FetchProjectDetailsByIdProjection model() {

        return new FetchProjectDetailsByIdProjection("name", GameType.MINECRAFT_JAVA);
    }
}
