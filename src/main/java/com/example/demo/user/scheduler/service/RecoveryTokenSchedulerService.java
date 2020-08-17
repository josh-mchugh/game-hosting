package com.example.demo.user.scheduler.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecoveryTokenSchedulerService implements IRecoveryTokenSchedulerService {

    private final IUserService userService;

    @Override
    public ImmutableList<User> processExpiredRecoveryTokens() {

        List<User> processedUsers = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<User> page = userService.getByRecoveryTokensExpired(pageRequest);

        while(page.hasContent()) {

            List<User> users = page.stream()
                    .map(user -> user.getRecoveryToken().getId())
                    .map(userService::handleDeleteRecoveryTokenById)
                    .collect(Collectors.toList());

            processedUsers.addAll(users);

            page = page.hasNext() ? userService.getByRecoveryTokensExpired(page.nextPageable()) : Page.empty();
        }

        return ImmutableList.copyOf(processedUsers);
    }
}
