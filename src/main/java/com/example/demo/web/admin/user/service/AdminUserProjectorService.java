package com.example.demo.web.admin.user.service;

import com.example.demo.project.entity.QProjectEntity;
import com.example.demo.project.entity.QProjectMembershipEntity;
import com.example.demo.user.entity.QUserEntity;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableQuery;
import com.example.demo.web.admin.user.service.model.FetchAdminUserTableResponse;
import com.example.demo.web.admin.user.service.projection.AdminUserProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserProjectorService implements IAdminUserProjectorService {

    private final JPQLQueryFactory queryFactory;

    @Override
    @QueryHandler
    public FetchAdminUserTableResponse getTable(FetchAdminUserTableQuery query) {

        QUserEntity qUser = QUserEntity.userEntity;
        QProjectMembershipEntity qProjectMembership = QProjectMembershipEntity.projectMembershipEntity;
        QProjectEntity qProject = QProjectEntity.projectEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        if(StringUtils.isNotBlank(query.getEmail())) {

            predicate.and(qUser.email.containsIgnoreCase(query.getEmail()));
        }

        if(CollectionUtils.isNotEmpty(query.getStates())) {

            predicate.and(qUser.state.in(query.getStates()));
        }

        if(CollectionUtils.isNotEmpty(query.getTypes())) {

            predicate.and(qUser.type.in(query.getTypes()));
        }

        Expression<Long> selectQuery = ExpressionUtils.as(JPAExpressions.select(qProject.id.count())
                .from(qProject)
                .innerJoin(qProject.projectMembershipsEntities, qProjectMembership)
                .where(qProjectMembership.userEntity.eq(qUser)), "projectCount");

        JPQLQuery<AdminUserProjection> jpqlQuery = queryFactory.select(Projections.constructor(
                    AdminUserProjection.class,
                    qUser.email,
                    qUser.state,
                    qUser.type,
                    selectQuery
                ))
                .from(qUser)
                .where(predicate)
                .offset(query.getPageable().getOffset())
                .limit(query.getPageable().getPageSize());

        if(query.getPageable().getSort().isSorted()) {

            Sort.Order order = query.getPageable().getSort().iterator().next();

            switch (order.getProperty()) {
                case "email":
                    jpqlQuery.orderBy(order.isAscending() ? qUser.email.asc() : qUser.email.desc());
                    break;
                case "state":
                    jpqlQuery.orderBy(order.isAscending() ? qUser.state.asc() : qUser.state.desc());
                    break;
                case "type":
                    jpqlQuery.orderBy(order.isAscending() ? qUser.type.asc() : qUser.type.desc());
                    break;
                case "projectCount":
                    jpqlQuery.orderBy(order.isAscending()
                            ? new OrderSpecifier<>(Order.ASC, new PathBuilder<>(Long.class, "projectCount"))
                            : new OrderSpecifier<>(Order.DESC, new PathBuilder<>(Long.class, "projectCount")));
                    break;
            }
        }

        return new FetchAdminUserTableResponse(new PageImpl<>(jpqlQuery.fetch(), query.getPageable(), jpqlQuery.fetchCount()));
    }
}
