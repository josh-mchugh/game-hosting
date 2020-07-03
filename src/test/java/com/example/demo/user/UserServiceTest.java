package com.example.demo.user;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.model.User;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateAdminUser() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("admin@test.com")
                .password("password1")
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();

        User user = userService.handleCreateUser(request);

        Assertions.assertTrue(StringUtils.isNotEmpty(user.getId()));
        Assertions.assertEquals(user.getEmail(), request.getEmail());
        Assertions.assertTrue(StringUtils.isNotEmpty(user.getPassword()));
        Assertions.assertEquals(user.getState(), request.getState());
        Assertions.assertEquals(user.getType(), UserType.ADMIN);
        Assertions.assertEquals(user.getInvalidLoginAttempts(), 0L);
        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void testCreateRegularUser() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("regular@test.com")
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .build();

        User user = userService.handleCreateUser(request);

        Assertions.assertTrue(StringUtils.isNotEmpty(user.getId()));
        Assertions.assertEquals(user.getEmail(), request.getEmail());
        Assertions.assertTrue(StringUtils.isNotEmpty(user.getPassword()));
        Assertions.assertEquals(user.getState(), request.getState());
        Assertions.assertEquals(user.getType(), UserType.REGULAR);
        Assertions.assertEquals(user.getInvalidLoginAttempts(), 0L);
        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void testExistsUser() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("existUser@existUser.com")
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();

        userService.handleCreateUser(request);

        Boolean exists = userService.existUserByEmail(request.getEmail());

        Assertions.assertTrue(exists);
    }

    @Test
    public void testNotExistsUser() {

        Boolean exists = userService.existUserByEmail("notExistUser@notExistUser.com");

        Assertions.assertFalse(exists);
    }

    @Test
    public void testGetUserByEmail() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("test.user1@test.user1.com")
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();

        userService.handleCreateUser(request);

        User user = userService.getUserByEmail(request.getEmail());

        Assertions.assertEquals(user.getEmail(), request.getEmail());
    }

    @Test
    public void testGetUserByEmailNotExists() {

        User user = userService.getUserByEmail("notExistUser@notExistUser.com");

        Assertions.assertNull(user);
    }

    @Test
    public void testIncrementUserInvalidLogin() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("increment.invalid@test.com")
                .password("password")
                .type(UserType.REGULAR)
                .state(UserState.ACTIVE)
                .build();

        User user = userService.handleCreateUser(request);
        Assertions.assertEquals(user.getInvalidLoginAttempts(), 0L);

        user = userService.handleIncrementInvalidLogin(request.getEmail());
        Assertions.assertTrue(user.getInvalidLoginAttempts() > 0);

        user = userService.handleResetInvalidLogin(request.getEmail());
        Assertions.assertEquals(0L, user.getInvalidLoginAttempts());
    }
}
