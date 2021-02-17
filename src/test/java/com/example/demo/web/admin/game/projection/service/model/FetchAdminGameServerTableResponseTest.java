package com.example.demo.web.admin.game.projection.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

public class FetchAdminGameServerTableResponseTest {

    @Test
    public void whenModelHasPageThenReturnPage() {

        FetchAdminGameServerTableResponse model = new FetchAdminGameServerTableResponse(new PageImpl<>(new ArrayList<>()));

        Assertions.assertEquals(new PageImpl<>(new ArrayList<>()), model.getGameServers());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerTableResponse model = model();

        String expected = "FetchAdminGameServerTableResponse(gameServers=null)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerTableResponse model = model();

        Assertions.assertEquals(102, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerTableResponse model1 = model();
        FetchAdminGameServerTableResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerTableResponse model = model();

        Assertions.assertNotEquals(model, new FetchAdminGameServerTableResponse(new PageImpl<>(new ArrayList<>())));
    }

    private FetchAdminGameServerTableResponse model() {

        return new FetchAdminGameServerTableResponse(null);
    }
}
