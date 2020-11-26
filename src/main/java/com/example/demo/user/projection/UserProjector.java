package com.example.demo.user.projection;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.mapper.UserMapper;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenQuery;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenQuery;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProjector implements IUserProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsByEmail(String email) {

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
    public boolean existsByRecoveryToken(String token) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.token.eq(token))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public FetchUserIdByEmailProjection fetchUserIdByEmail(FetchUserIdByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.select(Projections.constructor(
                    FetchUserIdByEmailProjection.class,
                    qUser.id
                )).from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();
    }

    @Override
    public FetchUserIdByPasswordResetTokenProjection fetchUserIdByPasswordResetToken(FetchUserIdByPasswordResetTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.select(Projections.constructor(
                    FetchUserIdByPasswordResetTokenProjection.class,
                    qUser.id
                )).from(qUser)
                .where(qUser.recoveryTokenEntity.token.eq(query.getToken()))
                .fetchOne();
    }

    @Override
    public FetchUserIdByVerificationTokenProjection fetchUserIdByVerificationToken(FetchUserIdByVerificationTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        return queryFactory.select(Projections.constructor(
                    FetchUserIdByVerificationTokenProjection.class,
                    qUser.id
                )).from(qUser)
                .where(qUser.verificationEntity.token.eq(query.getToken()))
                .fetchOne();
    }

    @Override
    public User getUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity entity = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();

        return UserMapper.map(entity);
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
}
