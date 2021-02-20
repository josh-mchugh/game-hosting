package com.example.demo.web.registration.projection.service;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.registration.projection.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.projection.service.model.ExistsUserByEmailResponse;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationProjectorService implements IRegistrationProjectorService {

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
}
