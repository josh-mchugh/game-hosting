package com.example.demo.web.admin.user.service.projection;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminUserProjectionTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        AdminUserProjection model = new AdminUserProjection("email", null, null, null);

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelHasStateThenReturnState() {

        AdminUserProjection model = new AdminUserProjection(null, UserState.ACTIVE, null, null);

        Assertions.assertEquals(UserState.ACTIVE, model.getState());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        AdminUserProjection model = new AdminUserProjection(null, null, UserType.ADMIN, null);

        Assertions.assertEquals(UserType.ADMIN, model.getType());
    }
    
    @Test
    public void whenModelHasProjectCountThenReturnProjectCount() {

        AdminUserProjection model = new AdminUserProjection(null, null, null, 1L);

        Assertions.assertEquals(1L, model.getProjectCount());
    }

    @Test
    public void whenModelIsAdminIsFalse() {

        AdminUserProjection model = new AdminUserProjection(null, null, UserType.REGULAR, null);

        Assertions.assertFalse(model.isAdmin());
    }

    @Test
    public void whenModelIsAdminIsTrue() {

        AdminUserProjection model = new AdminUserProjection(null, null, UserType.ADMIN, null);

        Assertions.assertTrue(model.isAdmin());
    }

    @Test
    public void whenModelToString() {

        AdminUserProjection model = model();

        String expected = "AdminUserProjection(email=email, state=ACTIVE, type=ADMIN, projectCount=1)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        AdminUserProjection model = new AdminUserProjection("email", null, null, 1L);

        Assertions.assertEquals(1337077252, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        AdminUserProjection model1 = model();
        AdminUserProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test

    public void whenModelNotEquals() {

        AdminUserProjection model = model();

        Assertions.assertNotEquals(model, new AdminUserProjection(null, null, null, null));
    }

    private AdminUserProjection model() {

        return new AdminUserProjection("email", UserState.ACTIVE, UserType.ADMIN, 1L);
    }
}
