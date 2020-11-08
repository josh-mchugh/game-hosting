package com.example.demo.ovh.credential.entity.service;

import com.example.demo.ovh.credential.aggregate.event.CredentialCreatedEvent;
import com.example.demo.ovh.credential.entity.model.Credential;

public interface ICredentialService {

    Credential handleCreated(CredentialCreatedEvent event);
}
