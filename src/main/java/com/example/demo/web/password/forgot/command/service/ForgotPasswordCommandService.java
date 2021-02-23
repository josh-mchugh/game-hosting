package com.example.demo.web.password.forgot.command.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ForgotPasswordCommandService implements IForgotPasswordCommandService {

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
    public FetchUserIdByEmailResponse getUserIdByEmail(com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String id = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchUserIdByEmailResponse(id != null ? UUID.fromString(id) : null);
    }
}
