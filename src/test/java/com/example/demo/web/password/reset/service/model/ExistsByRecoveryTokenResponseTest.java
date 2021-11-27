package com.example.demo.web.password.reset.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExistsByRecoveryTokenResponseTest {

    @Test
    public void whenModelHasExistsThenReturnExists() {

        ExistsByRecoveryTokenResponse model = new ExistsByRecoveryTokenResponse(true);

        Assertions.assertTrue(model.exists());
    }

    @Test
    public void whenModelToString() {

        ExistsByRecoveryTokenResponse model = model();

        String expected = "ExistsByRecoveryTokenResponse(exists=false)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ExistsByRecoveryTokenResponse model = model();

        Assertions.assertEquals(156, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ExistsByRecoveryTokenResponse model1 = model();
        ExistsByRecoveryTokenResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ExistsByRecoveryTokenResponse model = model();

        Assertions.assertEquals(model, new ExistsByRecoveryTokenResponse(false));
    }

    private ExistsByRecoveryTokenResponse model() {

        return new ExistsByRecoveryTokenResponse(false);
    }
}
