package com.example.demo.user.scheduler.service.projector;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.UserService;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsQuery;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RecoveryTokenProjectorServiceFetchExpiredTokenUserIdsTest {

    @Autowired
    private RecoveryTokenProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private UserService userService;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchExpiredTokenUserIds(null));
    }

    @Test
    public void whenParamHasNullPageableThenExpectException() {

        FetchExpiredRecoveryTokenUserIdsQuery query = new FetchExpiredRecoveryTokenUserIdsQuery(null);
        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.fetchExpiredTokenUserIds(query));
    }

    @Test
    public void whenNoExpiredTokensExistsThenExpectEmptyPage() {

        FetchExpiredRecoveryTokenUserIdsQuery query = new FetchExpiredRecoveryTokenUserIdsQuery(PageRequest.of(0, 10));
        FetchExpiredRecoveryTokenUserIdsResponse response = service.fetchExpiredTokenUserIds(query);

        Assertions.assertTrue(response.getRecoveryTokens().isEmpty());
    }

    @Test
    public void whenExpiredTokensExistsThenExpectContent() {

        UserCreatedEvent event = UserCreatedEvent.builder()
                .id(UUID.randomUUID())
                .email("test@test")
                .password("password")
                .verification(UserCreatedEvent.createVerification())
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();

        User user = userService.handleCreated(event);

        UserRecoveryTokenCreatedEvent tokenCreatedEvent = UserRecoveryTokenCreatedEvent.builder()
                .id(user.getId())
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(1L))
                .build();
        userService.handleRecoveryTokenCreated(tokenCreatedEvent);

        FetchExpiredRecoveryTokenUserIdsQuery query = new FetchExpiredRecoveryTokenUserIdsQuery(PageRequest.of(0, 10));
        FetchExpiredRecoveryTokenUserIdsResponse response = service.fetchExpiredTokenUserIds(query);

        Assertions.assertTrue(response.getRecoveryTokens().hasContent());
    }
}
