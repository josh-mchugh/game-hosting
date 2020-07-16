package com.example.demo.recovery.service;

import com.example.demo.framework.properties.AppConfig;
import com.example.demo.recovery.entity.RecoveryTokenEntity;
import com.example.demo.recovery.mapper.RecoveryTokenMapper;
import com.example.demo.recovery.model.RecoveryToken;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class RecoveryTokenService implements IRecoveryTokenService {

    private final AppConfig appConfig;
    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public RecoveryToken handleCreateRecoveryToken(String email) {

        QUserEntity qUserEntity = QUserEntity.userEntity;

        UserEntity userEntity = queryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.email.eq(email))
                .fetchOne();

        RecoveryTokenEntity recoveryTokenEntity = Optional.ofNullable(userEntity.getRecoveryTokenEntity())
                .orElse(new RecoveryTokenEntity(userEntity));

        LocalDateTime now = LocalDateTime.now();
        recoveryTokenEntity.setToken(UUID.randomUUID().toString());
        recoveryTokenEntity.setSentDate(now);
        recoveryTokenEntity.setExpirationDate(now.plus(appConfig.getPassword().getRecoveryExpirationOffset(), ChronoUnit.MILLIS));

        entityManager.persist(recoveryTokenEntity);

        return RecoveryTokenMapper.map(recoveryTokenEntity);
    }

    @Override
    public boolean existsRecoveryToken(String id) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.id.eq(id))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public boolean existsExpiredRecoveryTokens() {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.expirationDate.before(LocalDateTime.now()))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public List<RecoveryToken> getExpiredRecoveryTokens() {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.select(qUser.recoveryTokenEntity)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.expirationDate.before(LocalDateTime.now()))
                .limit(20)
                .fetch()
                .stream()
                .map(RecoveryTokenMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void handleDeleteRecoveryToken(String id) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity entity = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.id.eq(id))
                .fetchOne();

        entity.setRecoveryTokenEntity(null);

        entityManager.persist(entity);
    }
}
