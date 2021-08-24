package com.example.demo.web.password.forgot.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ForgotPasswordQueryServiceImpl implements ForgotPasswordQueryService{

    private final JPQLQueryFactory queryFactory;

    @Override
    public ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchCount();

        return new ExistsUserByEmailResponse(count >= 1);
    }

    @Override
    public FetchUserIdByEmailResponse getUserIdByEmail(FetchUserIdByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String id = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchUserIdByEmailResponse(id != null ? UUID.fromString(id) : null);
    }
}
