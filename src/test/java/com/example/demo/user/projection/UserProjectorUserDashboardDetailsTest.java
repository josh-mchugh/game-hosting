package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.user.projection.model.FetchUserDashboardProjection;
import com.example.demo.user.projection.model.FetchUserDashboardQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectorUserDashboardDetailsTest {

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private IUserService userService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenQueryIsNullThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchUserDashboard(null));
    }

    @Test
    public void whenQueryHasNullEmailThenThrowException() {

        FetchUserDashboardQuery query = new FetchUserDashboardQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.fetchUserDashboard(query));
    }

    @Test
    public void whenQueryHasInvalidEmailThenReturnNull() {

        FetchUserDashboardQuery query = new FetchUserDashboardQuery("invalidEmail");

        FetchUserDashboardProjection projection = userProjector.fetchUserDashboard(query);

        Assertions.assertNull(projection);
    }

    @Test
    public void whenQueryHasValidEmailAndIsNotValidatedThenReturnIsEmailVerifiedFalse() {

        SampleData data = sampleBuilder.builder()
                .user()
                .build();

        FetchUserDashboardQuery query = new FetchUserDashboardQuery(data.getUser().getEmail());

        FetchUserDashboardProjection projection = userProjector.fetchUserDashboard(query);

        Assertions.assertFalse(projection.isEmailValidated());
    }

    @Test
    public void whenQueryHasValidEmailAndIsHasNoProjectsThenReturnIsProjectsFalse() {

        SampleData data = sampleBuilder.builder()
                .user()
                .build();

        FetchUserDashboardQuery query = new FetchUserDashboardQuery(data.getUser().getEmail());

        FetchUserDashboardProjection projection = userProjector.fetchUserDashboard(query);

        Assertions.assertFalse(projection.isProjects());
    }

    @Test
    public void whenQueryHasEmailAndIsValidatedThenReturnIsEmailVerifiedTrue() {

        SampleData data = sampleBuilder.builder()
                .user()
                .build();

        UserVerifiedEvent event = UserVerifiedEvent.builder()
                .id(UUID.fromString(data.getUser().getId()))
                .verifiedDate(LocalDateTime.now())
                .build();
        userService.handleVerified(event);

        FetchUserDashboardQuery query = new FetchUserDashboardQuery(data.getUser().getEmail());

        FetchUserDashboardProjection projection = userProjector.fetchUserDashboard(query);

        Assertions.assertTrue(projection.isEmailValidated());
    }

    @Test
    public void whenQueryHasEmailAndHasProjectsThenReturnIsProjectTrue() {

        SampleData data = sampleBuilder.builder()
                .user()
                .game()
                .project()
                .build();

        FetchUserDashboardQuery query = new FetchUserDashboardQuery(data.getUser().getEmail());

        FetchUserDashboardProjection projection = userProjector.fetchUserDashboard(query);

        Assertions.assertTrue(projection.isProjects());
    }
}
