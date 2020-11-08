package com.example.demo.ovh.credential.entity.service;

import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.credential.entity.mapper.CredentialMapper;
import com.example.demo.ovh.credential.entity.model.Credential;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CredentialService implements ICredentialService {

    private final EntityManager entityManager;

    @Override
    @EventHandler
    public Credential handleCreated(CredentialCreatedEvent event) {

        CredentialEntity entity = new CredentialEntity();
        entity.setId(event.getId());
        entity.setSshKeyId(event.getSshKeyId());
        entity.setName(event.getName());
        entity.setPublicKey(event.getPublicKey());
        entity.setType(event.getType());

        entityManager.persist(entity);

        return CredentialMapper.map(entity);
    }
}
