package com.example.demo.ovh.credential.entity.service;

import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.model.Credential;

public interface CredentialService {

    Credential handleCreated(CredentialCreatedEvent event);
}
