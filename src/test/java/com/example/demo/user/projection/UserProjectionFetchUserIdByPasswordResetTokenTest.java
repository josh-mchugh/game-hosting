package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionFetchUserIdByPasswordResetTokenTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.fetchUserIdByPasswordResetToken(null));
    }

    @Test
    public void whenParamHasNullTokenThenExpectException() {

        FetchUserIdByPasswordResetTokenQuery query = new FetchUserIdByPasswordResetTokenQuery(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.fetchUserIdByPasswordResetToken(query));
    }

    @Test
    public void whenEntityExistsThenReturnUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(200L))
                .build();

        User updatedUser = userService.handleRecoveryTokenCreated(event);

        FetchUserIdByPasswordResetTokenQuery query = new FetchUserIdByPasswordResetTokenQuery(updatedUser.getRecoveryToken().getToken());
        FetchUserIdByPasswordResetTokenProjection projection = userProjector.fetchUserIdByPasswordResetToken(query);

        Assertions.assertEquals(user.getId(), projection.getId());
    }

    @Test
    public void wheEntityDoesNotExistThenReturnNull() {

        FetchUserIdByPasswordResetTokenQuery query = new FetchUserIdByPasswordResetTokenQuery("token");
        FetchUserIdByPasswordResetTokenProjection projection = userProjector.fetchUserIdByPasswordResetToken(query);

        Assertions.assertNull(projection);
    }
}
