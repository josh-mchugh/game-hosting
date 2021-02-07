package com.example.demo.game.projection.model;

import com.example.demo.game.entity.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerGameProjectionTest {

    @Test
    public void whenProjectionHasIdThenReturnId() {

        AdminGameServerGameProjection projection = new AdminGameServerGameProjection("id", null);

        Assertions.assertEquals("id", projection.getId());
    }

    @Test
    public void whenProjectionHasTypeThenReturnType() {

        AdminGameServerGameProjection projection = new AdminGameServerGameProjection(null, GameType.MINECRAFT_JAVA);

        Assertions.assertEquals(GameType.MINECRAFT_JAVA, projection.getType());
    }

    @Test
    public void whenProjectionToString() {

        AdminGameServerGameProjection projection = projection();

        String expected = "AdminGameServerGameProjection(id=id, type=MINECRAFT_JAVA)";

        Assertions.assertEquals(expected, projection.toString());
    }

    @Test
    public void whenProjectionHashCode() {

        AdminGameServerGameProjection projection = new AdminGameServerGameProjection("id", null);

        Assertions.assertEquals(201469, projection.hashCode());
    }

    @Test
    public void whenProjectionEquals() {

        AdminGameServerGameProjection projection1 = projection();
        AdminGameServerGameProjection projection2 = projection();

        Assertions.assertEquals(projection1, projection2);
    }

    @Test
    public void whenProjectionNotEquals() {

        AdminGameServerGameProjection projection = projection();

        Assertions.assertNotEquals(projection, new AdminGameServerGameProjection(null, null));
    }

    private AdminGameServerGameProjection projection() {

        return new AdminGameServerGameProjection("id", GameType.MINECRAFT_JAVA);
    }
}
