package com.example.demo.framework.security.user.projection.projection;

import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDetailsProjectionTest {

    @Test
    public void whenModelHasEmailThenReturnEmail() {

        UserDetailsProjection model = new UserDetailsProjection("email", null, null);

        Assertions.assertEquals("email", model.getEmail());
    }

    @Test
    public void whenModelHasPasswordThenReturnPassword() {

        UserDetailsProjection model = new UserDetailsProjection(null, "password", null);

        Assertions.assertEquals("password", model.getPassword());
    }

    @Test
    public void whenModelHasTypeThenReturnType() {

        UserDetailsProjection model = new UserDetailsProjection(null, null, UserType.REGULAR);

        Assertions.assertEquals("REGULAR", model.getType());
    }

    @Test
    public void whenModelToString() {

        UserDetailsProjection model = model();

        String expected = "UserDetailsProjection(email=email, password=password, type=ADMIN)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        UserDetailsProjection model = model();

        Assertions.assertEquals(174803815, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        UserDetailsProjection model1 = model();
        UserDetailsProjection model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        UserDetailsProjection model = model();

        Assertions.assertNotEquals(model, new UserDetailsProjection(null, null, null));
    }

    private UserDetailsProjection model() {

        return new UserDetailsProjection("email", "password", UserType.ADMIN);
    }
}
