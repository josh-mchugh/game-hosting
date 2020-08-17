package com.example.demo.user;

import com.example.demo.sample.TestUserUtil;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateAdminUser() {

        UserCreateRequest request = TestUserUtil.createAdminUser("admin@test.com");
        User user = userService.handleCreateUser(request);

        Assertions.assertTrue(StringUtils.isNotEmpty(user.getId()));
        Assertions.assertEquals(request.getEmail(), user.getEmail());
        Assertions.assertTrue(StringUtils.isNotEmpty(user.getPassword()));
        Assertions.assertEquals(request.getState() ,user.getState());
        Assertions.assertEquals(UserType.ADMIN, user.getType());
        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void testCreateRegularUser() {

        UserCreateRequest request = TestUserUtil.createUser("regular@test.com");
        User user = userService.handleCreateUser(request);

        Assertions.assertTrue(StringUtils.isNotEmpty(user.getId()));
        Assertions.assertEquals(request.getEmail(), user.getEmail());
        Assertions.assertTrue(StringUtils.isNotEmpty(user.getPassword()));
        Assertions.assertEquals(request.getState(), user.getState());
        Assertions.assertEquals(UserType.REGULAR, user.getType());
        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void testExistsByEmail() {

        UserCreateRequest request = TestUserUtil.createUser("existUser@existUser.com");
        userService.handleCreateUser(request);

        boolean exists = userService.existsUserByEmail(request.getEmail());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testNotExistsByEmail() {

        boolean exists = userService.existsUserByEmail("notExistUser@notExistUser.com");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testExistsByVerificationToken() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("exists-by-verification-token@user-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        boolean exists = userService.existsByVerificationToken(user.getVerification().getToken());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testNotExistsByVerificationToken () {

        boolean exists = userService.existsByVerificationToken("ASDFASDFASDFASF");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testGetUserByEmail() {

        UserCreateRequest request = TestUserUtil.createUser("test.user1@test.user1.com");
        userService.handleCreateUser(request);

        User user = userService.getUserByEmail(request.getEmail());

        Assertions.assertEquals(request.getEmail(), user.getEmail());
    }

    @Test
    public void testGetUserByEmailNotExists() {

        User user = userService.getUserByEmail("notExistUser@notExistUser.com");

        Assertions.assertNull(user);
    }

    @Test
    public void testHandleAuthenticationSuccess() {

        UserCreateRequest request = TestUserUtil.createUser("authentication-success@user-service.com");
        User user = userService.handleCreateUser(request);

        user = userService.handleAuthenticationSuccess(user.getEmail());
        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
        Assertions.assertNotNull(user.getLastLoginDate());
    }

    @Test
    public void testHandleAuthenticationFailure() {

        UserCreateRequest request = TestUserUtil.createUser("authentication-failure@user-service.com");
        User user = userService.handleCreateUser(request);

        user = userService.handleAuthenticationFailure(user.getEmail());
        Assertions.assertTrue(user.getInvalidLoginAttempts() > 0);
    }

    @Test
    public void testPasswordReset() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("password-reset@user-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleCreateRecoveryToken(user.getEmail());

        UserPasswordResetRequest passwordResetRequest = UserPasswordResetRequest.builder()
                .token(user.getRecoveryToken().getToken())
                .password("Password2!")
                .build();

        User updatedUser = userService.handlePasswordReset(passwordResetRequest);

        Assertions.assertEquals(user.getId(), updatedUser.getId());
        Assertions.assertNotEquals(user.getPassword(), updatedUser.getPassword());
        Assertions.assertNotNull(user.getRecoveryToken());
        Assertions.assertNull(updatedUser.getRecoveryToken());
    }

    @Test
    public void testHandleEmailVerification() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("handle-email-verification@user-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        User updatedUser = userService.handleEmailVerification(user.getVerification().getToken());

        Assertions.assertEquals(user.getId(), updatedUser.getId());
        Assertions.assertEquals(user.getVerification().getId(), updatedUser.getVerification().getId());
        Assertions.assertEquals(VerificationStatus.VERIFIED, updatedUser.getVerification().getStatus());
        Assertions.assertNotNull(updatedUser.getVerification().getSentDate());
    }

    @Test
    public void testHandleResetEmailVerification() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("handle-reset-email-verification@user-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        User updatedUser = userService.handleResetEmailVerification(user.getId());

        Assertions.assertEquals(user.getId(), updatedUser.getId());
        Assertions.assertEquals(user.getVerification().getId(), updatedUser.getVerification().getId());
        Assertions.assertNotEquals(user.getVerification().getToken(), updatedUser.getVerification().getToken());
        Assertions.assertNotEquals(user.getVerification().getSentDate(), updatedUser.getVerification().getSentDate());
        Assertions.assertEquals(VerificationStatus.UNVERIFIED, updatedUser.getVerification().getStatus());
        Assertions.assertNull(updatedUser.getVerification().getVerificationDate());
        Assertions.assertFalse(user.getVerification().isVerified());
    }

    @Test
    public void testHandleDeleteRecoveryTokenById() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("delete-recovery-token-by-id@user-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleCreateRecoveryToken(user.getEmail());

        User updatedUser = userService.handleDeleteRecoveryTokenById(user.getRecoveryToken().getId());

        Assertions.assertEquals(user.getId(), updatedUser.getId());
        Assertions.assertNotNull(user.getRecoveryToken());
        Assertions.assertNull(updatedUser.getRecoveryToken());
    }

    @Test
    public void testHandleCreateRecoveryToken() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user1@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleCreateRecoveryToken(user.getEmail());

        Assertions.assertNotNull(user.getRecoveryToken().getId());
        Assertions.assertNotNull(user.getRecoveryToken().getToken());
        Assertions.assertNotNull(user.getRecoveryToken().getSentDate());
        Assertions.assertNotNull(user.getRecoveryToken().getExpirationDate());
        Assertions.assertTrue(user.getRecoveryToken().getExpirationDate().isAfter(user.getRecoveryToken().getSentDate()));
    }

    @Test
    public void testGetByRecoveryTokenExpired() throws Exception {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user3@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        userService.handleCreateRecoveryToken(user.getEmail());

        Thread.sleep(201);

        Page<User> expiredRecoveryTokens = userService.getByRecoveryTokensExpired(PageRequest.of(0, 20));

        Assertions.assertTrue(expiredRecoveryTokens.getContent().size() >= 1);
    }

    @Test
    public void testExistsByRecoveryToken() {

        UserCreateRequest userCreateRequest = TestUserUtil.createUser("user4@recovery-token-service.com");
        User user = userService.handleCreateUser(userCreateRequest);

        user = userService.handleCreateRecoveryToken(user.getEmail());

        boolean exists = userService.existsByRecoveryToken(user.getRecoveryToken().getToken());

        Assertions.assertTrue(exists);
    }
}
