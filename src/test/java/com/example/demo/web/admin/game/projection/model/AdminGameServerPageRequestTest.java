package com.example.demo.web.admin.game.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

public class AdminGameServerPageRequestTest {

    @Test
    public void whenModelHasPageableThenReturnPageable() {

        AdminGameServerPageRequest model = new AdminGameServerPageRequest(PageRequest.of(1, 20));

        Assertions.assertEquals(PageRequest.of(1, 20), model.getPageable());
    }

    @Test
    public void whenModelToString() {

        AdminGameServerPageRequest model = model();

        String expected = "AdminGameServerPageRequest(pageable=Page request [number: 1, size 20, sort: UNSORTED])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminGameServerPageRequest model = model();

        Assertions.assertEquals(31959, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminGameServerPageRequest model1 = model();
        AdminGameServerPageRequest model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        AdminGameServerPageRequest model = model();

        Assertions.assertNotEquals(model, new AdminGameServerPageRequest(null));
    }

    private AdminGameServerPageRequest model() {

        return new AdminGameServerPageRequest(PageRequest.of(1, 20));
    }
}
