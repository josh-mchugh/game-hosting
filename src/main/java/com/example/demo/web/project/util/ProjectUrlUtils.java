package com.example.demo.web.project.util;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import com.example.demo.web.project.util.model.ProjectStateValidationRequest;
import com.example.demo.web.project.util.model.ProjectStateValidationResponse;
import org.springframework.stereotype.Component;

@Component("projectUrlUtils")
public class ProjectUrlUtils {

    public String getProjectUrl(String id, ProjectStatus status, ProjectState state) {

        if (status == ProjectStatus.CONFIG) {

            return buildProjectConfigUrl(id, state);
        }

        return String.format("/project/dashboard/%s", id);
    }

    public ProjectStateValidationResponse isValidStatusAndState(ProjectStateValidationRequest request) {

        if (request.getCurrentStatus().equals(request.getExpectedStatus())) {

            boolean stateEqual = request.getCurrentState().equals(request.getExpectedState());
            boolean currentStateGreater = request.getCurrentState().ordinal() > request.getExpectedState().ordinal();

            if(stateEqual || currentStateGreater) {

                return ProjectStateValidationResponse.builder()
                        .valid(true)
                        .build();
            }
        }

        return ProjectStateValidationResponse.builder()
                .valid(false)
                .redirectUrl(getProjectUrl(request.getId().toString(), request.getCurrentStatus(), request.getCurrentState()))
                .build();
    }

    private String buildProjectConfigUrl(String id, ProjectState state) {

        switch (state) {
            case CONFIG_SERVER:
                return String.format("/project/create/%s/server", id);
            case CONFIG_BILLING:
                return String.format("/project/create/%s/billing", id);
            default:
                return String.format("/project/create/%s/region", id);
        }
    }
}
