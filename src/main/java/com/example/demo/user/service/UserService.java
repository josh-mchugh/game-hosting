package com.example.demo.user.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.RecoveryTokenEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.VerificationEntity;
import com.example.demo.user.entity.VerificationStatus;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final AppConfig appConfig;
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
                .where(qUser.recoveryTokenEntity.token.eq(request.getToken()))
                .fetchOne();

        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRecoveryTokenEntity(null);

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

    @Override
    public User handleCreateRecoveryToken(String email) {

        UserEntity userEntity = findUserByEmail(email);

        RecoveryTokenEntity recoveryTokenEntity = new RecoveryTokenEntity();
        recoveryTokenEntity.setUserEntity(userEntity);
        recoveryTokenEntity.setToken(UUID.randomUUID().toString());

        LocalDateTime now = LocalDateTime.now();
        recoveryTokenEntity.setSentDate(now);
        recoveryTokenEntity.setExpirationDate(now.plus(appConfig.getPassword().getRecoveryExpirationOffset(), ChronoUnit.MILLIS));

        userEntity.setRecoveryTokenEntity(recoveryTokenEntity);

        entityManager.persist(userEntity);

        return UserMapper.map(userEntity);
    }

    @Override
    public User handleDeleteRecoveryTokenById(String id) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity entity = queryFactory.selectFrom(qUser)
                .where(qUser.recoveryTokenEntity.id.eq(id))
                .fetchOne();

        entity.setRecoveryTokenEntity(null);

        entityManager.persist(entity);

        return UserMapper.map(entity);
    }

    @Override
    public boolean existsByRecoveryToken(String token) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.token.eq(token))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public Page<User> getByRecoveryTokensExpired(Pageable pageable) {

        QUserEntity qUser = QUserEntity.userEntity;

        JPQLQuery<UserEntity> query = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.expirationDate.before(LocalDateTime.now()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        List<UserEntity> entities = query.fetch();


        return new PageImpl<>(UserMapper.map(entities), pageable, query.fetchCount());
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
