package com.example.demo.user.scheduler;

import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.scheduler.service.IRecoveryTokenSchedulerService;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest(properties = {"app.password.recovery-expiration-offset=1"})
@Transactional
@ActiveProfiles({"test"})
public class RecoveryTokenSchedulerTest {

    @Autowired
    private IRecoveryTokenSchedulerService recoveryTokenSchedulerService;

    @Autowired
    private IUserService userService;

    @Test
    public void whenExpiredRecoveryTokenDoNotExistThenReturnEmptyArray() {

        ImmutableList<User> processedUsers = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(0, processedUsers.size());
    }

    @Test
    public void whenExpiredRecoveryTokenExistsThenReturnArray(){

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("test@test");
        User user = userService.handleCreateUser(userCreateRequest);

        userService.handleCreateRecoveryToken(user.getEmail());

        ImmutableList<User> processedUser = recoveryTokenSchedulerService.processExpiredRecoveryTokens();

        Assertions.assertEquals(1, processedUser.size());
    }
}
