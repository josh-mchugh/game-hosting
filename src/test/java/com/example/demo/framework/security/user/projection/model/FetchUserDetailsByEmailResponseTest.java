package com.example.demo.framework.security.user.projection.model;

import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
import com.example.demo.user.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchUserDetailsByEmailResponseTest {

    @Test
    public void whenModelHasUserDetailsThenReturnUserDetails() {

        FetchUserDetailsByEmailResponse model = new FetchUserDetailsByEmailResponse(new UserDetailsProjection("email", "password", UserType.ADMIN));

        Assertions.assertEquals(new UserDetailsProjection("email", "password", UserType.ADMIN), model.getUserDetails());
    }

    @Test
    public void whenModelToString() {

        FetchUserDetailsByEmailResponse model = model();

        String expected = "FetchUserDetailsByEmailResponse(userDetails=UserDetailsProjection(email=email, password=password, type=ADMIN))";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchUserDetailsByEmailResponse model = model();

        Assertions.assertEquals(174803874, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchUserDetailsByEmailResponse model1 = model();
        FetchUserDetailsByEmailResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchUserDetailsByEmailResponse model = model();

        Assertions.assertNotEquals(model, new FetchUserDetailsByEmailResponse(null));
    }

    private FetchUserDetailsByEmailResponse model() {

        return new FetchUserDetailsByEmailResponse(new UserDetailsProjection("email", "password", UserType.ADMIN));
    }
}
