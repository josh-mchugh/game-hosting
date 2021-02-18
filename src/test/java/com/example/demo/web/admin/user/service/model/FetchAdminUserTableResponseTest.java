package com.example.demo.web.admin.user.service.model;

import com.example.demo.web.admin.user.service.projection.AdminUserProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

public class FetchAdminUserTableResponseTest {

    @Test
    public void whenModelHasPageThenReturnPage() {

        Page<AdminUserProjection> page = new PageImpl<>(new ArrayList<>());

        FetchAdminUserTableResponse model = new FetchAdminUserTableResponse(page);

        Assertions.assertEquals(page, model.getPage());
    }

    @Test
    public void whenModelToString() {

        FetchAdminUserTableResponse model = model();

        String expected = "FetchAdminUserTableResponse(page=Page 1 of 1 containing UNKNOWN instances)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchAdminUserTableResponse model = new FetchAdminUserTableResponse(null);

        Assertions.assertEquals(102, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchAdminUserTableResponse model1 = model();
        FetchAdminUserTableResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchAdminUserTableResponse model = model();

        Assertions.assertNotEquals(model, new FetchAdminUserTableResponse(null));
    }

    private FetchAdminUserTableResponse model() {

        Page<AdminUserProjection> page = new PageImpl<>(new ArrayList<>());

        return new FetchAdminUserTableResponse(page);
    }
}
