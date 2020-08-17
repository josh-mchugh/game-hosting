package com.example.demo.user.mapper;

import com.example.demo.user.entity.RecoveryTokenEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.model.User;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class UserMappterTest {

    @Test
    public void whenEntityIsNullThenReturnNull() {

        Assertions.assertNull(UserMapper.map((UserEntity) null));
    }

    @Test
    public void whenEntityIsNotNullThenReturnNotNull() {

        Assertions.assertNotNull(UserMapper.map(new UserEntity()));
    }

    @Test
    public void whenEntityHasIdThenReturnId() {

        UserEntity entity = new UserEntity();
        entity.setId("id");

        User user = UserMapper.map(entity);

        Assertions.assertEquals("id", user.getId());
    }

    @Test
    public void whenEntityHasNullIdThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getId());
    }

    @Test
    public void whenEntityHasEmailThenReturnEmail() {

        UserEntity entity = new UserEntity();
        entity.setEmail("email");

        User user = UserMapper.map(entity);

        Assertions.assertEquals("email", user.getEmail());
    }

    @Test
    public void whenEntityHasNullEmailThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getEmail());
    }

    @Test
    public void whenEntityHasPasswordThenReturnPassword() {

        UserEntity entity = new UserEntity();
        entity.setPassword("password");

        User user = UserMapper.map(entity);

        Assertions.assertEquals("password", user.getPassword());
    }

    @Test
    public void whenEntityHasNullPasswordThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getPassword());
    }

    @Test
    public void whenEntityHasStateThenReturnState() {

        UserEntity entity = new UserEntity();
        entity.setState(UserState.ACTIVE);

        User user = UserMapper.map(entity);

        Assertions.assertEquals(UserState.ACTIVE, user.getState());
    }

    @Test
    public void whenEntityHasNullStateThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getState());
    }

    @Test
    public void whenEntityHasTypeThenReturnType() {

        UserEntity entity = new UserEntity();
        entity.setType(UserType.REGULAR);

        User user = UserMapper.map(entity);

        Assertions.assertEquals(UserType.REGULAR, user.getType());
    }

    @Test
    public void whenEntityHasNullTypeThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getType());
    }

    @Test
    public void whenEntityHasInvalidLoginAttemptsThenReturnInvalidLoginAttempts() {

        UserEntity entity = new UserEntity();
        entity.setInvalidLoginAttempts(1L);

        User user = UserMapper.map(entity);

        Assertions.assertEquals(1L, user.getInvalidLoginAttempts());
    }

    @Test
    public void whenEntityHasNullInvalidLoginAttemptsThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getInvalidLoginAttempts());
    }

    @Test
    public void whenEntityHasLastLoginThenReturnLastLogin() {

        LocalDateTime lastLogin = LocalDateTime.now();

        UserEntity entity = new UserEntity();
        entity.setLastLoginDate(lastLogin);

        User user = UserMapper.map(entity);

        Assertions.assertEquals(lastLogin, user.getLastLoginDate());
    }

    @Test
    public void whenEntityHasNullLastLoginThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getLastLoginDate());
    }

    @Test
    public void whenEntityHasRecoveryTokenThenReturnRecoveryToken() {

        UserEntity userEntity = new UserEntity();
        userEntity.setRecoveryTokenEntity(new RecoveryTokenEntity());

        User user = UserMapper.map(userEntity);

        Assertions.assertNotNull(user.getRecoveryToken());
    }

    @Test
    public void whenEntityHasNullRecoveryTokenThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getRecoveryToken());
    }

    @Test
    public void whenEntityHasVerificationThenReturnVerification() {

        UserEntity entity = new UserEntity();
        entity.setVerificationEntity(new VerificationEntity());

        User user = UserMapper.map(entity);

        Assertions.assertNotNull(user.getVerification());
    }

    @Test
    public void whenEntityHasNullVerificationThenReturnNull() {

        User user = UserMapper.map(new UserEntity());

        Assertions.assertNull(user.getVerification());
    }

    @Test
    public void whenEntitiesIsNullThenReturnEmptyList() {

        ImmutableList<User> users = UserMapper.map((Collection) null);

        Assertions.assertEquals(0, users.size());
    }

    @Test
    public void whenEntitiesIsEmptyListThenReturnEmptyList() {

        ImmutableList<User> users = ImmutableList.of();

        Assertions.assertEquals(0, users.size());
    }

    @Test
    public void whenEntitiesThenReturnImmutableList() {

        ImmutableList<User> users = UserMapper.map(Collections.singleton(new UserEntity()));

        Assertions.assertEquals(1, users.size());
    }
}
