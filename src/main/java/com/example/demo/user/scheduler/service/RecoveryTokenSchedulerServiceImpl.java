package com.example.demo.user.scheduler.service;

import com.example.demo.user.aggregate.command.UserRecoveryTokenDeleteCommand;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsQuery;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsResponse;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class RecoveryTokenSchedulerServiceImpl implements RecoveryTokenSchedulerService {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<Object> processExpiredRecoveryTokens() throws ExecutionException, InterruptedException {

        List<Object> processedUsers = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<UUID> page = fetchExpiredTokens(pageRequest);

        while(page.hasContent()) {

            for(UUID id : page.getContent()) {

                commandGateway.send(new UserRecoveryTokenDeleteCommand(id));
                processedUsers.add(id);
            }

            page = page.hasNext() ? fetchExpiredTokens(page.nextPageable()) : Page.empty();
        }

        return ImmutableList.copyOf(processedUsers);
    }

    private Page<UUID> fetchExpiredTokens(Pageable pageable) throws ExecutionException, InterruptedException {

        FetchExpiredRecoveryTokenUserIdsQuery query = new FetchExpiredRecoveryTokenUserIdsQuery(pageable);
        FetchExpiredRecoveryTokenUserIdsResponse response = queryGateway.query(query, FetchExpiredRecoveryTokenUserIdsResponse.class).get();

        return response.getRecoveryTokens();
    }
}
