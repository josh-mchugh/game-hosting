package com.example.demo.email.service;

import com.example.demo.email.entity.EmailEntity;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.QEmailEntity;
import com.example.demo.email.mapper.EmailMapper;
import com.example.demo.email.model.Email;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.email.service.model.EmailProcessedRequest;
import com.example.demo.framework.config.AppConfig;
import com.google.common.collect.ImmutableList;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@Transactional
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final AppConfig appConfig;
    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    public Email handleCreateEmail(EmailCreateRequest request) {

        EmailEntity entity = new EmailEntity();
        entity.setTemplate(request.getTemplate());
        entity.setStatus(EmailStatus.PENDING);
        entity.setToAddress(request.getToAddress());
        entity.setContext(request.getContext());

        entityManager.persist(entity);

        return EmailMapper.map(entity);
    }

    @Override
    public Boolean existsPendingEmails() {

        QEmailEntity qEmail = QEmailEntity.emailEntity;

        long count = queryFactory.select(qEmail.id)
                .from(qEmail)
                .where(qEmail.status.eq(EmailStatus.PENDING))
                .fetchCount();

        return count >= 1;
    }

    @Override
    public ImmutableList<Email> getPendingEmails() {

        QEmailEntity qEmail = QEmailEntity.emailEntity;

        return queryFactory.selectFrom(qEmail)
                .where(qEmail.status.eq(EmailStatus.PENDING))
                .limit(appConfig.getEmail().getPagingSize())
                .orderBy(qEmail.createdDate.asc())
                .fetch()
                .stream()
                .map(EmailMapper::map)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public Email handleProcessedEmail(EmailProcessedRequest request) {

        QEmailEntity qEmail = QEmailEntity.emailEntity;

        EmailEntity entity = queryFactory.selectFrom(qEmail)
                .where(qEmail.id.eq(request.getId()))
                .fetchOne();

        entity.setStatus(request.getStatus());

        if(EmailStatus.SENT.equals(request.getStatus())) {

            entity.setMailingDate(LocalDateTime.now());
        }

        entityManager.persist(entity);

        return EmailMapper.map(entity);
    }
}
