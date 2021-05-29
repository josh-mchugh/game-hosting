package com.example.demo.web.project.util;

import com.example.demo.project.entity.ProjectState;
import com.example.demo.project.entity.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectUrlUtilTest {

    @Test
    public void whenParamHasNullIdThenExpectNullInUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl(null, ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/null", url);
    }

    @Test
    public void whenParamHasNullStatusThenExpectDashboardUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl(null, ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/null", url);
    }

    @Test
    public void whenParamHasConfigStatusAndNullStateThenExpectException() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        Assertions.assertThrows(NullPointerException.class, () -> util.getProjectUrl("id", ProjectStatus.CONFIG, null));
    }

    @Test
    public void whenProjectHasStatusNotConfigThenExpectDashboardUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl("id", ProjectStatus.ACTIVE, ProjectState.ACTIVE);

        Assertions.assertEquals("/project/dashboard/id", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigRegionThenExpectConfigRegionUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_REGION);

        Assertions.assertEquals("/project/create/id/region", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigServerThenExpectConfigServerUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_SERVER);

        Assertions.assertEquals("/project/create/id/server", url);
    }

    @Test
    public void whenProjectHasStatusConfigAndStateConfigBillingThenExpectConfigBillingUrl() {

        ProjectUrlUtil util = new ProjectUrlUtil();

        String url = util.getProjectUrl("id", ProjectStatus.CONFIG, ProjectState.CONFIG_BILLING);

        Assertions.assertEquals("/project/create/id/billing", url);
    }
}
