package com.example.demo.web.password.reset.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenResponse;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsByRecoveryTokenResponse existsByRecoveryToken(ExistsByRecoveryTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.token.eq(query.getToken()))
                .fetchCount();

        return new ExistsByRecoveryTokenResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchUserIdByRecoveryTokenResponse getUserIdByToken(FetchUserIdByRecoveryTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String id = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.token.eq(query.getToken()))
                .fetchOne();

        return new FetchUserIdByRecoveryTokenResponse(id != null ? UUID.fromString(id) : null);
    }
}
