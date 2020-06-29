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
    public void testCreateUser() {

        UserCreateRequest request = UserCreateRequest.builder()
                .email("test@test.com")
                .password("password1")
                .state(UserState.ACTIVE)
                .type(UserType.REGULAR)
                .build();

        User user = userService.handleCreateUser(request);

        Assertions.assertTrue(StringUtils.isNotEmpty(user.getId()));
        Assertions.assertEquals(user.getEmail(), request.getEmail());
        Assertions.assertEquals(user.getState(), request.getState());
        Assertions.assertEquals(user.getType(), request.getType());
        Assertions.assertEquals(user.getInvalidLoginAttempts(), 0L);
        Assertions.assertNull(user.getLastLoginDate());
    }
}
