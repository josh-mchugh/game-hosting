package com.example.demo.web.admin.game.projection.service.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminGameServerFlavorProjectionTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        AdminGameServerFlavorProjection model = new AdminGameServerFlavorProjection("id", null, null, null);

        Assertions.assertEquals("id", model.getId());
    }

    @Test
    public void whenModelHasNameThenReturnName() {

        AdminGameServerFlavorProjection model = new AdminGameServerFlavorProjection(null, "name", null, null);

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasVcpusThenReturnVcpus() {

        AdminGameServerFlavorProjection model = new AdminGameServerFlavorProjection(null, null,  1, null);

        Assertions.assertEquals(1, model.getVcpus());
    }

    @Test
    public void whenModelHasRamThenReturnRam() {

        AdminGameServerFlavorProjection model = new AdminGameServerFlavorProjection(null, null, null, 1);

        Assertions.assertEquals(1, model.getRam());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerFlavorProjection model = model();

        String expected = "AdminGameServerFlavorProjection(id=id, name=name, vcpus=1, ram=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerFlavorProjection model = model();

        Assertions.assertEquals(-439863855, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerFlavorProjection model1 = model();
        AdminGameServerFlavorProjection model2 = model();

        Assertions.assertEquals(model1,  model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerFlavorProjection model =model();

        Assertions.assertNotEquals(model, new AdminGameServerFlavorProjection(null, null, null, null));
    }

    private AdminGameServerFlavorProjection model() {

        return new AdminGameServerFlavorProjection("id", "name", 1, 1);
    }
}
