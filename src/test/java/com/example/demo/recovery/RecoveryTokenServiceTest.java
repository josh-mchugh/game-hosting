package com.example.demo.recovery;

import com.example.demo.recovery.model.RecoveryToken;
import com.example.demo.recovery.service.IRecoveryTokenService;
import com.example.demo.test.TestUserUtil;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class RecoveryTokenServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecoveryTokenService recoveryTokenService;

    @Test
    public void testCreateRecoveryToken() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user1@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        Assertions.assertNotNull(recoveryToken.getId());
        Assertions.assertNotNull(recoveryToken.getToken());
        Assertions.assertNotNull(recoveryToken.getSentDate());
        Assertions.assertNotNull(recoveryToken.getExpirationDate());
        Assertions.assertTrue(recoveryToken.getExpirationDate().isAfter(recoveryToken.getSentDate()));
    }

    @Test
    public void testExistsExpiredRecoveryToken() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user2@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        Thread.sleep(201);

        boolean existsExpired = recoveryTokenService.existsExpiredRecoveryTokens();

        Assertions.assertTrue(existsExpired);
    }

    @Test
    public void testGetExpiredRecoveryTokens() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user3@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        Thread.sleep(201);

        List<RecoveryToken> expiredRecoveryTokens = recoveryTokenService.getExpiredRecoveryTokens();

        Assertions.assertTrue(expiredRecoveryTokens.size() >= 1);
    }

    @Test
    public void testExistsRecoveryToken() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user4@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        RecoveryToken recoveryToken = recoveryTokenService.handleCreateRecoveryToken(user.getEmail());

        boolean exists = recoveryTokenService.existsRecoveryToken(recoveryToken.getId());

        Assertions.assertTrue(exists);
    }
}
