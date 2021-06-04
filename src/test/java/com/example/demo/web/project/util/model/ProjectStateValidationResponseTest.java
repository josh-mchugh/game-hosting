package com.example.demo.web.project.util.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectStateValidationResponseTest {

    @Test
    public void whenModelHasValidThenValid() {

        ProjectStateValidationResponse model = ProjectStateValidationResponse.builder()
                .valid(true)
                .build();

        Assertions.assertTrue(model.isValid());
    }

    @Test
    public void whenModelHasRedirectUrlThenReturnRedirectUrl() {

        ProjectStateValidationResponse model = ProjectStateValidationResponse.builder()
                .redirectUrl("redirectUrl")
                .build();

        Assertions.assertEquals("redirectUrl", model.getRedirectUrl());
    }

    @Test
    public void whenModelToString() {

        ProjectStateValidationResponse model = model();

        String expected = "ProjectStateValidationResponse(valid=false, redirectUrl=redirectUrl)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProjectStateValidationResponse model = model();

        Assertions.assertEquals(1970346983, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProjectStateValidationResponse model1 = model();
        ProjectStateValidationResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProjectStateValidationResponse model = model();

        Assertions.assertNotEquals(model, ProjectStateValidationResponse.builder().build());
    }

    private ProjectStateValidationResponse model() {

        return ProjectStateValidationResponse.builder()
                .valid(false)
                .redirectUrl("redirectUrl")
                .build();
    }
}
