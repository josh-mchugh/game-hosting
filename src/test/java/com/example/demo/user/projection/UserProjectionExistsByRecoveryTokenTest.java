package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
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
public class UserProjectionExistsByRecoveryTokenTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.existsByRecoveryToken(null));
    }

    @Test
    public void whenEntityExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(UUID.fromString(user.getId()))
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(0L))
                .build();

        User updatedUser = userService.handleRecoveryTokenCreated(event);

        Assertions.assertTrue(userProjector.existsByRecoveryToken(updatedUser.getRecoveryToken().getToken()));
    }

    @Test
    public void whenEntityDoesNotExistThenReturnFalse() {

        Assertions.assertFalse(userProjector.existsByRecoveryToken("token"));
    }
}
