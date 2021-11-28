package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailResponse;
import com.example.demo.user.entity.QUserEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureProjectorServiceImpl implements AuthenticationFailureProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchCount();

        return new ExistsUserByEmailResponse(count >= 1);
    }

    @Override
    @QueryHandler
    public FetchAuthFailureByEmailResponse fetchAuthFailureByEmail(FetchAuthFailureByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String id = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchAuthFailureByEmailResponse(id != null ? UUID.fromString(id) : null);
    }
}
