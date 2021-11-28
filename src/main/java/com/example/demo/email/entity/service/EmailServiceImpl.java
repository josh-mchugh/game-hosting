package com.example.demo.email.entity.service;

import com.example.demo.email.aggregate.event.EmailCreatedEvent;
import com.example.demo.email.aggregate.event.EmailFailedEvent;
import com.example.demo.email.aggregate.event.EmailSentEvent;
import com.example.demo.email.entity.EmailEntity;
import com.example.demo.email.entity.EmailStatus;
import com.example.demo.email.entity.QEmailEntity;
import com.example.demo.email.entity.mapper.EmailMapper;
import com.example.demo.email.entity.model.Email;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JPQLQueryFactory queryFactory;
    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Email handleCreated(EmailCreatedEvent event) {

        EmailEntity entity = new EmailEntity();
        entity.setId(event.getId());
        entity.setTemplate(event.getTemplate());
        entity.setStatus(EmailStatus.QUEUED);
        entity.setToAddress(event.getToAddress());
        entity.setBodyContext(event.getBodyContext());
        entity.setSubjectContext(event.getSubjectContext());

        entityManager.persist(entity);

        return EmailMapper.map(entity);
    }

    @Override
    @EventHandler
    public Email handleSent(EmailSentEvent event) {

        QEmailEntity qEmail = QEmailEntity.emailEntity;

        EmailEntity entity = queryFactory.selectFrom(qEmail)
                .where(qEmail.id.eq(event.getId().toString()))
                .fetchOne();

        entity.setStatus(EmailStatus.SENT);
        entity.setSentDate(event.getSentDate());

        entityManager.persist(entity);

        return EmailMapper.map(entity);
    }

    @Override
    @EventHandler
    public Email handleFailed(EmailFailedEvent event) {

        QEmailEntity qEmail = QEmailEntity.emailEntity;

        EmailEntity entity = queryFactory.selectFrom(qEmail)
                .where(qEmail.id.eq(event.getId().toString()))
                .fetchOne();

        entity.setStatus(EmailStatus.FAILED);

        entityManager.persist(entity);

        return EmailMapper.map(entity);
    }
}
