package com.example.demo.web.dashboard.projection.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.project.aggregate.event.ProjectCreatedEvent;
import com.example.demo.project.entity.model.Project;
import com.example.demo.project.entity.service.IProjectService;
import com.example.demo.project.projection.model.ProjectDashboardProjection;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.dashboard.projection.service.model.DashboardDetailsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class DashboardProjectionServiceDashboardDetailsTest {

    @Autowired
    private IDashboardProjectionService dashboardService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ISessionUtil sessionUtil;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .credential()
                .game()
                .awxOrganization()
                .awxCredential()
                .awxInventory()
                .awxProject()
                .awxPlaybook()
                .build();
    }

    @Test
    public void whenDashboardDetailsHasVerifiedUserThenReturnEmailVerifiedTrue() {

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(data.getUser().getId())
                .verifiedDate(LocalDateTime.now())
                .build();

        userService.handleVerified(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasProjectsThenReturnProjectsTrue() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertTrue(detailsResponse.isHasProjects());
    }

    @Test
    public void whenDashboardDetailsHasProjectsSizeThenReturnProjectsSize() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(1, detailsResponse.getProjects().size());
    }

    @Test
    public void whenDashboardDetailsHasProjectThenProjectEqualsExpected() {

        ProjectCreatedEvent event = ProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .name("name")
                .gameId(data.getGame().getId())
                .member(ProjectCreatedEvent.createMember(data.getUser().getId()))
                .build();

        Project project = projectService.handleCreated(event);

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        ProjectDashboardProjection expectedProject = new ProjectDashboardProjection(project.getId().toString(), project.getName(), data.getGame().getType());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertEquals(expectedProject, detailsResponse.getProjects().get(0));
    }

    @Test
    public void whenDashboardDetailsHasNonVerifiedUserThenReturnEmailVerifiedFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isEmailVerified());
    }

    @Test
    public void whenDashboardDetailsHasNoProjectsThenReturnHasProjectsFalse() {

        Mockito.when(sessionUtil.getCurrentUserEmail()).thenReturn(data.getUser().getEmail());

        DashboardDetailsResponse detailsResponse = dashboardService.getDashboardDetails();

        Assertions.assertFalse(detailsResponse.isHasProjects());
    }
}
