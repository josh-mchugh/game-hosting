package com.example.demo.framework.security.user.projection;

import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailQuery;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailResponse;
import com.example.demo.framework.security.user.projection.projection.UserDetailsProjection;
import com.example.demo.user.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsProjectorServiceImpl implements UserDetailsProjectorService {

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
    public FetchUserDetailsByEmailResponse fetchUserDetails(FetchUserDetailsByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserDetailsProjection projection = queryFactory.select(
                Projections.constructor(
                    UserDetailsProjection.class,
                    qUser.email,
                    qUser.password,
                    qUser.type
                ))
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchUserDetailsByEmailResponse(projection);
    }
}
