package com.example.demo.web.admin.game.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

public class FetchAdminGameServerTableQueryTest {

    @Test
    public void whenModelHasPageableThenReturnPageable() {

        FetchAdminGameServerTableQuery model = new FetchAdminGameServerTableQuery(PageRequest.of(1, 10));

        Assertions.assertEquals(PageRequest.of(1, 10), model.getPageable());
    }

    @Test
    public void whenModelToString() {

        FetchAdminGameServerTableQuery model = model();

        String expected = "FetchAdminGameServerTableQuery(pageable=Page request [number: 1, size 10, sort: UNSORTED])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminGameServerTableQuery model = model();

        Assertions.assertEquals(31649, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminGameServerTableQuery model1 = model();
        FetchAdminGameServerTableQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminGameServerTableQuery model = model();

        Assertions.assertNotEquals(model, new FetchAdminGameServerTableQuery(null));
    }

    private FetchAdminGameServerTableQuery model() {

        return new FetchAdminGameServerTableQuery(PageRequest.of(1, 10));
    }
}
