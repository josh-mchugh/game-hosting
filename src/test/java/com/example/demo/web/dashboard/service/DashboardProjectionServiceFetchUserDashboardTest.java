package com.example.demo.web.dashboard.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class DashboardProjectionServiceFetchUserDashboardTest {

    @Autowired
    private IDashboardProjectionService service;

    @Autowired
    private IUserService userService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserDashboard(null));
    }

    @Test
    public void whenParamHasNullEmailThenExpectException() {

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchUserDashboard(query));
    }

    @Test
    public void whenUserHasNotVerifiedEmailThenExpectEmailEmailVerifiedFalse() {

        User user = sampleBuilder.builder()
            .user()
            .build()
            .getUser();

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(user.getEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        Assertions.assertFalse(response.isEmailVerified());
    }

    @Test
    public void whenUserHasVerifiedEmailThenExpectEmailEmailVerifiedTrue() {

        User user = sampleBuilder.builder()
            .user()
            .build()
            .getUser();

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(user.getId())
                .verifiedDate(LocalDateTime.now())
                .build();
        userService.handleVerified(event);

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(user.getEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        Assertions.assertTrue(response.isEmailVerified());
    }

    @Test
    public void whenUserDoesNotHaveProjectsThenExpectProjectsFalse() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(user.getEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        Assertions.assertFalse(response.isHasProjects());
    }

    @Test
    public void whenUserHasProjectsThenExpectProjectsTrue() {

        User user = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build()
                .getUser();

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(user.getEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        Assertions.assertTrue(response.isHasProjects());
    }

    @Test
    public void whenUserHasProjectsThenExpectProjects() {

        User user = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build()
                .getUser();

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(user.getEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getProjects()));
    }
}
