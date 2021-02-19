package com.example.demo.web.verification.projection.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenResponse;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerifyProjectorService implements IVerifyProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsUserByVerifyTokenResponse existsByToken(ExistsUserByVerifyTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.verificationEntity.token.eq(query.getToken()))
                .fetchCount();

        return new ExistsUserByVerifyTokenResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchUserIdByVerificationTokenResponse fetchUserIdByVerificationToken(FetchUserIdByVerificationTokenQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String userId = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.verificationEntity.token.eq(query.getToken()))
                .fetchOne();

        return new FetchUserIdByVerificationTokenResponse(userId != null ? UUID.fromString(userId) : null);
    }
}
