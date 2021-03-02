package com.example.demo.user.scheduler.service;

import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsQuery;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsResponse;
import com.google.common.collect.ImmutableList;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles({"test"})
public class RecoveryTokenSchedulerServiceTest {

    @Autowired
    private IRecoveryTokenSchedulerService recoveryTokenSchedulerService;

    @MockBean
    private QueryGateway queryGateway;

    @MockBean
    private CommandGateway commandGateway;

    @Test
    public void whenExpiredRecoveryTokenDoNotExistThenReturnEmptyArray() throws ExecutionException, InterruptedException {

        Pageable pageable = PageRequest.of(0, 20);
        Mockito.when(queryGateway.query(new FetchExpiredRecoveryTokenUserIdsQuery(pageable), FetchExpiredRecoveryTokenUserIdsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchExpiredRecoveryTokenUserIdsResponse(new PageImpl<>(new ArrayList<>()))));

        ImmutableList<Object> processedUsers = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(0, processedUsers.size());
    }

    @Test
    public void whenExpiredRecoveryTokenExistsThenReturnArray() throws ExecutionException, InterruptedException {

        Pageable pageable = PageRequest.of(0, 20);
        Mockito.when(queryGateway.query(new FetchExpiredRecoveryTokenUserIdsQuery(pageable), FetchExpiredRecoveryTokenUserIdsResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchExpiredRecoveryTokenUserIdsResponse(new PageImpl<>(Collections.singletonList(UUID.randomUUID())))));

        Mockito.when(commandGateway.sendAndWait(Mockito.any())).thenReturn(UUID.randomUUID());

        ImmutableList<Object> processedUser = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(1, processedUser.size());
    }
}
