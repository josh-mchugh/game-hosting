package com.example.demo.user.projection;

import com.example.demo.user.entity.QUserEntity;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.entity.mapper.UserMapper;
import com.example.demo.user.entity.model.User;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserProjector implements IUserProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        long count = queryFactory.select(qUser.id)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public User getUserByEmail(String email) {

        QUserEntity qUser = QUserEntity.userEntity;

        UserEntity entity = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchOne();

        return UserMapper.map(entity);
    }

    @Override
    public Page<User> getByRecoveryTokensExpired(Pageable pageable) {

        QUserEntity qUser = QUserEntity.userEntity;

        JPQLQuery<UserEntity> query = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.recoveryTokenEntity.expirationDate.before(LocalDateTime.now()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        List<UserEntity> entities = query.fetch();

        return new PageImpl<>(UserMapper.map(entities), pageable, query.fetchCount());
    }
}
