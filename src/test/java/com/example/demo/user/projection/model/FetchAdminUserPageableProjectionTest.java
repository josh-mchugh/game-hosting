package com.example.demo.user.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

public class FetchAdminUserPageableProjectionTest {

    @Test
    public void whenModelHasPageThenReturnPage() {

        Page<AdminUserProjection> page = new PageImpl<>(new ArrayList<>());

        FetchAdminUserPageableProjection model = new FetchAdminUserPageableProjection(page);

        Assertions.assertEquals(page, model.getPage());
    }

    @Test
    public void whenModelToString() {

        FetchAdminUserPageableProjection model = model();

        String expected = "FetchAdminUserPageableProjection(page=Page 1 of 1 containing UNKNOWN instances)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminUserPageableProjection model = new FetchAdminUserPageableProjection(null);

        Assertions.assertEquals(102, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminUserPageableProjection model1 = model();
        FetchAdminUserPageableProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminUserPageableProjection model = model();

        Assertions.assertNotEquals(model, new FetchAdminUserPageableProjection(null));
    }

    private FetchAdminUserPageableProjection model() {

        Page<AdminUserProjection> page = new PageImpl<>(new ArrayList<>());

        return new FetchAdminUserPageableProjection(page);
    }
}
