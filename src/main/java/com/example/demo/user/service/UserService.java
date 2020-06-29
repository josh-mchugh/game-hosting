package com.example.demo.user.service;

import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final EntityManager entityManager;

    @Override
    public User handleCreateUser(UserCreateRequest request) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity.setState(UserState.ACTIVE);
        userEntity.setType(UserType.REGULAR);
        userEntity.setInvalidLoginAttempts(0L);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }
}
