package com.example.demo.user.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

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

        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setUserEntity(userEntity);
        verificationEntity.setSentDate(LocalDateTime.now());
        verificationEntity.setToken(UUID.randomUUID().toString());
        verificationEntity.setStatus(VerificationStatus.UNVERIFIED);

        userEntity.setVerificationEntity(verificationEntity);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public boolean existsUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsByVerificationToken(String token) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.verificationEntity.token.eq(token))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public User getUserByEmail(String email) {

        UserEntity userEntity = findUserByEmail(email);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleAuthenticationFailure(String email) {

        UserEntity userEntity = findUserByEmail(email);
        userEntity.setInvalidLoginAttempts(userEntity.getInvalidLoginAttempts() + 1);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleAuthenticationSuccess(String email) {

        UserEntity userEntity = findUserByEmail(email);
        userEntity.setInvalidLoginAttempts(0L);
        userEntity.setLastLoginDate(LocalDateTime.now());

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handlePasswordReset(UserPasswordResetRequest request) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity userEntity = queryFactory.selectFrom(qUser)
                .where(qUser.recoveryTokenEntity.id.eq(request.getRecoveryTokenId()))
                .fetchOne();

        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleEmailVerification(String token) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity userEntity = queryFactory.selectFrom(qUser)
                .where(qUser.verificationEntity.token.eq(token))
                .fetchOne();

        userEntity.getVerificationEntity().setStatus(VerificationStatus.VERIFIED);
        userEntity.getVerificationEntity().setVerificationDate(LocalDateTime.now());

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleResetEmailVerification(String id) {

        UserEntity userEntity = findUserById(id);
        userEntity.getVerificationEntity().setToken(UUID.randomUUID().toString());
        userEntity.getVerificationEntity().setStatus(VerificationStatus.UNVERIFIED);
        userEntity.getVerificationEntity().setSentDate(LocalDateTime.now());
        userEntity.getVerificationEntity().setVerificationDate(null);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    private UserEntity findUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.selectFrom(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();
    }

    private UserEntity findUserById(String id) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(id))
                .fetchOne();
    }
}
