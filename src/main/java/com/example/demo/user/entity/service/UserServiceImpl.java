package com.example.demo.user.entity.service;

import com.example.demo.user.aggregate.event.UserAuthFailedEvent;
import com.example.demo.user.aggregate.event.UserAuthSuccessEvent;
import com.example.demo.user.aggregate.event.UserCreatedEvent;
import com.example.demo.user.aggregate.event.UserPasswordChangedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.aggregate.event.UserRecoveryTokenDeletedEvent;
import com.example.demo.user.aggregate.event.UserVerifiedEvent;
import com.example.demo.user.aggregate.event.UserVerifyResetEvent;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.RecoveryTokenEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.user.entity.mapper.UserMapper;
import com.example.demo.user.entity.model.User;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public User handleCreated(UserCreatedEvent event) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(event.getId());
        userEntity.setEmail(event.getEmail());
        userEntity.setPassword(event.getPassword());
        userEntity.setState(event.getState());
        userEntity.setType(event.getType());
        userEntity.setInvalidLoginAttempts(0L);

        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setId(event.getVerification().getId());
        verificationEntity.setUserEntity(userEntity);
        verificationEntity.setToken(event.getVerification().getToken());
        verificationEntity.setStatus(VerificationStatus.UNVERIFIED);
        verificationEntity.setSentDate(event.getVerification().getSentDate());

        userEntity.setVerificationEntity(verificationEntity);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    @EventHandler
    public User handleAuthFailed(UserAuthFailedEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.setInvalidLoginAttempts(entity.getInvalidLoginAttempts() + 1);

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    @EventHandler
    public User handleAuthSuccess(UserAuthSuccessEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.setInvalidLoginAttempts(0L);
        entity.setLastLoginDate(event.getLastLoginDate());

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    @EventHandler
    public User handleVerified(UserVerifiedEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.getVerificationEntity().setStatus(VerificationStatus.VERIFIED);
        entity.getVerificationEntity().setVerificationDate(event.getVerifiedDate());

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    @EventHandler
    public User handleVerifyReset(UserVerifyResetEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.getVerificationEntity().setToken(event.getToken());
        entity.getVerificationEntity().setStatus(VerificationStatus.UNVERIFIED);
        entity.getVerificationEntity().setSentDate(event.getSentDate());
        entity.getVerificationEntity().setVerificationDate(null);

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    @EventHandler
    public User handlePasswordChanged(UserPasswordChangedEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.setPassword(event.getPassword());
        entity.setRecoveryTokenEntity(null);

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    @EventHandler
    public User handleRecoveryTokenCreated(UserRecoveryTokenCreatedEvent event) {

        UserEntity userEntity = findUserById(event.getId());

        RecoveryTokenEntity recoveryTokenEntity = new RecoveryTokenEntity();
        recoveryTokenEntity.setId(event.getRecoveryToken().getId());
        recoveryTokenEntity.setUserEntity(userEntity);
        recoveryTokenEntity.setToken(event.getRecoveryToken().getToken());
        recoveryTokenEntity.setSentDate(event.getRecoveryToken().getSentDate());
        recoveryTokenEntity.setExpirationDate(event.getRecoveryToken().getExpirationDate());

        userEntity.setRecoveryTokenEntity(recoveryTokenEntity);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    @EventHandler
    public User handleRecoveryTokenDelete(UserRecoveryTokenDeletedEvent event) {

        UserEntity entity = findUserById(event.getId());
        entity.setRecoveryTokenEntity(null);

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    private UserEntity findUserById(UUID id) {

        return findUserById(id.toString());
    }

    private UserEntity findUserById(String id) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(id))
                .fetchOne();
    }
}
