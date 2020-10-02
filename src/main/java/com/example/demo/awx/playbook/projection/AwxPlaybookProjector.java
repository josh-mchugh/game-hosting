package com.example.demo.awx.playbook.projection;

import com.example.demo.awx.playbook.entity.QAwxPlayBookEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxPlaybookProjector implements IAwxPlaybookProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxPlayBookEntity qAwxPlayBook = QAwxPlayBookEntity.awxPlayBookEntity;

        long count = queryFactory.select(qAwxPlayBook)
                .from(qAwxPlayBook)
                .fetchCount();

        return count >= 1;
    }
}
