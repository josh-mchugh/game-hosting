package com.example.demo.web.project.util;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectUrlUtilsGetProjectUrlTest {

    @Test
    public void whenParamHasNullIdThenExpectNullInUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl(null, ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/null", url);
    }

    @Test
    public void whenParamHasNullStatusThenExpectDashboardUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl(null, ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/null", url);
    }

    @Test
    public void whenParamHasConfigStatusAndNullStateThenExpectException() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        Assertions.assertThrows(NullPointerException.class, () -> util.getProjectUrl("id", ProjectStatus.CONFIG, null));
    }

    @Test
    public void whenProjectHasStatusNotConfigThenExpectDashboardUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl("id", ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/id", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigRegionThenExpectConfigRegionUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_REGION);

        Assertions.assertEquals("/project/create/id/region", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigServerThenExpectConfigServerUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_SERVER);

        Assertions.assertEquals("/project/create/id/server", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigBillingThenExpectConfigBillingUrl() {

        ProjectUrlUtils util = new ProjectUrlUtils();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_BILLING);

        Assertions.assertEquals("/project/create/id/billing", url);
    }
}
