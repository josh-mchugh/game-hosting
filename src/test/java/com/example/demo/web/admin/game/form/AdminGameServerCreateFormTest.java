package com.example.demo.web.admin.game.form;

import com.example.demo.game.entity.GameServerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AdminGameServerCreateFormTest {

    @Test
    public void whenModelHasNameThenReturnName() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setName("name");

        Assertions.assertEquals("name", model.getName());
    }

    @Test
    public void whenModelHasDescriptionThenReturnDescription() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setDescription("description");

        Assertions.assertEquals("description", model.getDescription());
    }

    @Test
    public void whenModelHasSelectedStatusThenReturnSelectedStatus() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setSelectedStatus(GameServerStatus.ACTIVE);

        Assertions.assertEquals(GameServerStatus.ACTIVE, model.getSelectedStatus());
    }

    @Test
    public void whenModelHasGameIdThenReturnGameId() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setGameId("gameId");

        Assertions.assertEquals("gameId", model.getGameId());
    }

    @Test
    public void whenModelHasRegionIdThenReturnRegionId() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setRegionId("regionId");

        Assertions.assertEquals("regionId", model.getRegionId());
    }

    @Test
    public void whenModelHasFlavorIdThenReturnFlavorId() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setFlavorId("flavorId");

        Assertions.assertEquals("flavorId", model.getFlavorId());
    }

    @Test
    public void whenModelHasImageIdThenReturnImageId() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setImageId("imageId");

        Assertions.assertEquals("imageId", model.getImageId());
    }

    @Test
    public void whenModelHasStatusesThenReturnStatues() {

        Assertions.assertEquals(Arrays.asList(GameServerStatus.values()), new AdminGameServerCreateForm().getStatuses());
    }

    @Test
    public void whenIsDefaultStatusIsActiveThenReturnTrue() {

        Assertions.assertTrue(new AdminGameServerCreateForm().isDefaultStatus(GameServerStatus.ACTIVE));
    }

    @Test
    public void whenIsDefaultStatusIsNotActiveThenReturnFalse() {

        Assertions.assertFalse(new AdminGameServerCreateForm().isDefaultStatus(GameServerStatus.INACTIVE));
    }

    @Test
    public void whenModelToString() {

        AdminGameServerCreateForm model = model();

        String expected = "AdminGameServerCreateForm(name=name, description=description, selectedStatus=ACTIVE, gameId=gameId, regionId=regionId, flavorId=flavorId, imageId=imageId, statuses=[ACTIVE, INACTIVE])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setName("name");
        model.setDescription("description");
        model.setSelectedStatus(null);
        model.setGameId("gameId");
        model.setRegionId("regionId");
        model.setFlavorId("flavorId");
        model.setImageId("imageId");
        model.setStatuses(null);

        Assertions.assertEquals(-1380455489, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerCreateForm model1 = model();
        AdminGameServerCreateForm model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerCreateForm model = model();

        Assertions.assertNotEquals(model, new AdminGameServerCreateForm());
    }

    private AdminGameServerCreateForm model() {

        AdminGameServerCreateForm model = new AdminGameServerCreateForm();
        model.setName("name");
        model.setDescription("description");
        model.setSelectedStatus(GameServerStatus.ACTIVE);
        model.setGameId("gameId");
        model.setRegionId("regionId");
        model.setFlavorId("flavorId");
        model.setImageId("imageId");

        return model;
    }
}
