package com.example.demo.web.dashboard.command.service.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchGameIdByGameTypeQueryTest {

    @Test
    public void whenModelHasTypeThenReturnType() {

        FetchGameIdByGameTypeQuery model = new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, model.getType());
    }

    @Test
    public void whenModelToString() {

        FetchGameIdByGameTypeQuery model = model();

        String expected = "FetchGameIdByGameTypeQuery(type=MINECRAFT_JAVA)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchGameIdByGameTypeQuery model = new FetchGameIdByGameTypeQuery(null);

        Assertions.assertEquals(102, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchGameIdByGameTypeQuery model1 = model();
        FetchGameIdByGameTypeQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchGameIdByGameTypeQuery model = model();

        Assertions.assertNotEquals(model, new FetchGameIdByGameTypeQuery(null));
    }

    private FetchGameIdByGameTypeQuery model() {

        return new FetchGameIdByGameTypeQuery(GameType.MINECRAFT_JAVA);
    }
}
