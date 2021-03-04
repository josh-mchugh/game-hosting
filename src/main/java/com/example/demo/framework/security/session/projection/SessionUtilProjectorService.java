package com.example.demo.framework.security.session.projection;

import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailResponse;
import com.example.demo.user.entity.QUserEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionUtilProjectorService implements ISessionUtilProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchUserIdByEmailResponse fetchUserIdByEmail(FetchUserIdByEmailQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;

        String id = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(query.getEmail()))
                .fetchOne();

        return new FetchUserIdByEmailResponse(id != null ? UUID.fromString(id) : null);
    }
}
