package com.example.demo.web.password.reset.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.IUserService;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class ResetPasswordServiceGetUserIdByTokenTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private ResetPasswordService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getUserIdByToken(null));
    }

    @Test
    public void whenTokenIsNullThenExpectException() {

        FetchUserIdByRecoveryTokenQuery query = new FetchUserIdByRecoveryTokenQuery(null);

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.getUserIdByToken(query));
    }

    @Test
    public void whenTokenIsValidThenExpectUserId() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(user.getId())
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(200L))
                .build();

        User updatedUser = userService.handleRecoveryTokenCreated(event);

        FetchUserIdByRecoveryTokenQuery query = new FetchUserIdByRecoveryTokenQuery(updatedUser.getRecoveryToken().getToken());
        FetchUserIdByRecoveryTokenResponse projection = service.getUserIdByToken(query);

        Assertions.assertEquals(user.getId(), projection.getId());
    }

    @Test
    public void whenTokenIsInvalidThenExpectNullUserId() {

        FetchUserIdByRecoveryTokenQuery query = new FetchUserIdByRecoveryTokenQuery("token");
        FetchUserIdByRecoveryTokenResponse projection = service.getUserIdByToken(query);

        Assertions.assertNull(projection.getId());
    }
}
