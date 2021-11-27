package com.example.demo.web.admin.game.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerImageProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AdminGameServerImageProjection model = new AdminGameServerImageProjection("id", null);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AdminGameServerImageProjection model = new AdminGameServerImageProjection(null, "name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerImageProjection model = model();

        String expected = "AdminGameServerImageProjection(id=id, name=name)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerImageProjection model = model();

        Assertions.assertEquals(3575133, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerImageProjection model1 = model();
        AdminGameServerImageProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerImageProjection model = model();

        Assertions.assertNotEquals(model, new AdminGameServerImageProjection(null, null));
    }

    private AdminGameServerImageProjection model() {

        return new AdminGameServerImageProjection("id", "name");
    }
}
