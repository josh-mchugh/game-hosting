package com.example.demo.web.project.util;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.web.project.util.model.ProjectStateValidationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ProjectUrlUtilsIsValidStatusAndStateTest {

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> new ProjectUrlUtils().isValidStatusAndState(null));
    }

    @Test
    public void whenParamHasNullCurrentStatusThenExpectException() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .currentStatus(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> new ProjectUrlUtils().isValidStatusAndState(request));
    }

    @Test
    public void whenParamHasNullExpectedStatusThenExpectException() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .currentStatus(ProjectStatus.CONFIG)
                .expectedStatus(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> new ProjectUrlUtils().isValidStatusAndState(request));
    }

    @Test
    public void whenParamHasNullCurrentStateThenExpectException() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(null)
                .expectedStatus(ProjectStatus.CONFIG)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> new ProjectUrlUtils().isValidStatusAndState(request));
    }

    @Test
    public void whenParamHasNullExpectedStateThenExpectException() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(null)
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> new ProjectUrlUtils().isValidStatusAndState(request));
    }

    @Test
    public void whenParamHasNullIdThenExpectNullInUrl() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(null)
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_REGION)
                .build();

        Assertions.assertDoesNotThrow(() -> new ProjectUrlUtils().isValidStatusAndState(request));
    }

    @Test
    public void whenRequestStatusAndStateAreEqualThenExpectValidTrue() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(UUID.randomUUID())
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_REGION)
                .build();

        Assertions.assertTrue(new ProjectUrlUtils().isValidStatusAndState(request).isValid());
    }

    @Test
    public void whenRequestStatusIsEqualAndStateIsGreaterThenExpectValidTrue() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(UUID.randomUUID())
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_REGION)
                .build();

        Assertions.assertTrue(new ProjectUrlUtils().isValidStatusAndState(request).isValid());
    }

    @Test
    public void whenRequestStatusIsEqualAndStateIsLesserThenExpectValidFalse() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(UUID.randomUUID())
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertFalse(new ProjectUrlUtils().isValidStatusAndState(request).isValid());
    }

    @Test
    public void whenRequestStatusIsEqualAndStateIsLesserThenExpectRedirectUrl() {

        UUID id = UUID.randomUUID();

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(id)
                .currentStatus(ProjectStatus.CONFIG)
                .currentState(ProjectState.CONFIG_REGION)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertEquals(String.format("/project/create/%s/region", id), new ProjectUrlUtils().isValidStatusAndState(request).getRedirectUrl());
    }

    @Test
    public void whenRequestStatusIsNotEqualThenExpectValidFalse() {

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(UUID.randomUUID())
                .currentStatus(ProjectStatus.BUILD)
                .currentState(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertFalse(new ProjectUrlUtils().isValidStatusAndState(request).isValid());
    }

    @Test
    public void whenRequestStatusIsNotEqualThenExpectRedirectUrl() {

        UUID id = UUID.randomUUID();

        ProjectStateValidationRequest request = ProjectStateValidationRequest.builder()
                .id(id)
                .currentStatus(ProjectStatus.BUILD)
                .currentState(ProjectState.BUILD_CREATE_INSTANCE_GROUP)
                .expectedStatus(ProjectStatus.CONFIG)
                .expectedState(ProjectState.CONFIG_BILLING)
                .build();

        Assertions.assertEquals(String.format("/project/dashboard/%s", id), new ProjectUrlUtils().isValidStatusAndState(request).getRedirectUrl());
    }
}
