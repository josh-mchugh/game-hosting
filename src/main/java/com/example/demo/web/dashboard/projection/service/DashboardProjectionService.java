package com.example.demo.web.dashboard.projection.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.project.projection.IProjectProjector;
import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardProjection;
import com.example.demo.project.projection.model.FetchProjectDashboardQuery;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserDashboardProjection;
import com.example.demo.user.projection.model.FetchUserDashboardQuery;
import com.example.demo.web.dashboard.projection.service.model.DashboardDetailsResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardProjectionService implements IDashboardProjectionService {

    private final ISessionUtil sessionUtil;
    private final IUserProjector userProjector;
    private final IProjectProjector projectProjector;

    @Override
    public DashboardDetailsResponse getDashboardDetails() {

        FetchUserDashboardProjection userDashboardDetails = userDashboardDetailsProjection();

        DashboardDetailsResponse.Builder builder = DashboardDetailsResponse.builder()
                .emailVerified(userDashboardDetails.isEmailValidated())
                .hasProjects(userDashboardDetails.isProjects());

        if(userDashboardDetails.isProjects()) {

            builder.projects(getDashboardProjects());
        }

        return builder.build();
    }

    private FetchUserDashboardProjection userDashboardDetailsProjection() {

        return userProjector.fetchUserDashboard(new FetchUserDashboardQuery(sessionUtil.getCurrentUserEmail()));
    }

    private ImmutableList<ProjectDashboardProjection> getDashboardProjects() {

        FetchProjectDashboardQuery query = new FetchProjectDashboardQuery(sessionUtil.getCurrentUserEmail());

        FetchProjectDashboardProjection projection = projectProjector.fetchProjectDashboard(query);

        return projection.getProjects();
    }
}
