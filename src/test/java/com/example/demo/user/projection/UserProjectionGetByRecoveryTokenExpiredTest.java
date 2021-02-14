package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionGetByRecoveryTokenExpiredTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(NullPointerException.class, () -> userProjector.getByRecoveryTokensExpired(null));
    }

    @Test
    public void whenEntityHasExpiredRecoveryTokenThenReturnPageWithSize() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(user.getId())
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(0L))
                .build();

        userService.handleRecoveryTokenCreated(event);

        Page<User> page = userProjector.getByRecoveryTokensExpired(PageRequest.of(0, 1));

        Assertions.assertTrue(page.hasContent());
    }

    @Test
    public void whenEntityHasValidRecoveryTokenThenReturnEmptyPage() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(user.getId())
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1000L))
                .build();

        userService.handleRecoveryTokenCreated(event);

        Page<User> page = userProjector.getByRecoveryTokensExpired(PageRequest.of(0, 1));

        Assertions.assertFalse(page.hasContent());
    }
}
