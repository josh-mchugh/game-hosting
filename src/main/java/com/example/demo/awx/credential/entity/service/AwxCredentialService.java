package com.example.demo.awx.credential.entity.service;

import com.example.demo.awx.credential.aggregate.event.AwxCredentialCreatedEvent;
import com.example.demo.awx.credential.entity.model.AwxCredential;

public interface AwxCredentialService {

    AwxCredential handleCreated(AwxCredentialCreatedEvent event);
}
