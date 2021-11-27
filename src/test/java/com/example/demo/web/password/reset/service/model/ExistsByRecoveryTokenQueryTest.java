package com.example.demo.web.password.reset.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsByRecoveryTokenQueryTest {

    @Test
    public void whenModelHasTokenThenReturnToken() {

        ExistsByRecoveryTokenQuery model = new ExistsByRecoveryTokenQuery("token");

        Assertions.assertEquals("token", model.getToken());
    }

    @Test
    public void whenModelToString() {

        ExistsByRecoveryTokenQuery model = model();

        String expected = "ExistsByRecoveryTokenQuery(token=token)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsByRecoveryTokenQuery model = model();

        Assertions.assertEquals(110541364, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsByRecoveryTokenQuery model1 = model();
        ExistsByRecoveryTokenQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsByRecoveryTokenQuery model = model();

        Assertions.assertNotEquals(model, new ExistsByRecoveryTokenQuery(null));
    }

    private ExistsByRecoveryTokenQuery model() {

        return new ExistsByRecoveryTokenQuery("token");
    }
}
