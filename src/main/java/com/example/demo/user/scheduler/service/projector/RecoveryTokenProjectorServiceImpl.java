package com.example.demo.user.scheduler.service.projector;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsQuery;
import com.example.demo.user.scheduler.service.projector.model.FetchExpiredRecoveryTokenUserIdsResponse;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecoveryTokenProjectorServiceImpl implements RecoveryTokenProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchExpiredRecoveryTokenUserIdsResponse fetchExpiredTokenUserIds(FetchExpiredRecoveryTokenUserIdsQuery request) {

        QUserEntity qUser = QUserEntity.userEntity;

        JPQLQuery<String> query = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.expirationDate.before(LocalDateTime.now()))
                .limit(request.getPageable().getPageSize())
                .offset(request.getPageable().getOffset());

        List<UUID> ids = query.fetch().stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());

        return new FetchExpiredRecoveryTokenUserIdsResponse(new PageImpl<>(ids, request.getPageable(), query.fetchCount()));
    }
}
