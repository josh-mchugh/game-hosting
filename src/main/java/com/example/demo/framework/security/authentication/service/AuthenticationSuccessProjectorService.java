package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailResponse;
import com.example.demo.framework.security.authentication.service.projection.AuthSuccessProjection;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessProjectorService implements IAuthenticationSuccessProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchAuthSuccessByEmailResponse getUserAuthByEmail(FetchAuthSuccessByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        AuthSuccessProjection projection = queryFactory.select(Projections.constructor(
                    AuthSuccessProjection.class,
                    qUser.id,
                    new CaseBuilder().when(qUser.type.eq(UserType.ADMIN)).then(true).otherwise(false)
                ))
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchAuthSuccessByEmailResponse(projection);
    }
}
