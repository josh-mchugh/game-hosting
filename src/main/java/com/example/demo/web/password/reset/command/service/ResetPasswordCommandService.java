package com.example.demo.web.password.reset.command.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetPasswordCommandService implements IResetPasswordCommandService {

    private final JPQLQueryFactory queryFactory;

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
