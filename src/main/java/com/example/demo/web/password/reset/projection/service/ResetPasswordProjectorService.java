package com.example.demo.web.password.reset.projection.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPasswordProjectorService implements IResetPasswordProjectorService {

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
}
