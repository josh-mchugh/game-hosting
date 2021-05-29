package com.example.demo.web.project.util;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.springframework.stereotype.Component;

@Component("projectUrlUtil")
public class ProjectUrlUtil {

    public String getProjectUrl(String id, ProjectStatus status, ProjectState state) {

        if (status == ProjectStatus.CONFIG) {

            return buildProjectConfigUrl(id, state);
        }

        return String.format("/project/dashboard/%s", id);
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
