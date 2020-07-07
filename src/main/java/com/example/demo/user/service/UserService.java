package com.example.demo.user.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User handleCreateUser(UserCreateRequest request) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setState(request.getState());
        userEntity.setType(request.getType());
        userEntity.setInvalidLoginAttempts(0L);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public Boolean existUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public User getUserByEmail(String email) {

        UserEntity userEntity = findUserByEmail(email);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleIncrementInvalidLogin(String email) {

        UserEntity userEntity = findUserByEmail(email);
        userEntity.setInvalidLoginAttempts(userEntity.getInvalidLoginAttempts() + 1);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleResetInvalidLogin(String email) {

        UserEntity userEntity = findUserByEmail(email);
        userEntity.setInvalidLoginAttempts(0L);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    private UserEntity findUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();
    }
}
