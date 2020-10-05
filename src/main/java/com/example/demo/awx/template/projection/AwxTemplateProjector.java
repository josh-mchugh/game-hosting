package com.example.demo.awx.template.projection;

import com.example.demo.awx.template.entity.QAwxTemplateEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwxTemplateProjector implements IAwxTemplateProjector {

    private final JPQLQueryFactory queryFactory;

    @Override
    public boolean existsAny() {

        QAwxTemplateEntity qAwxTemplate = QAwxTemplateEntity.awxTemplateEntity;

        long count = queryFactory.select(qAwxTemplate)
                .from(qAwxTemplate)
                .fetchCount();

        return count >= 1;
    }
}
