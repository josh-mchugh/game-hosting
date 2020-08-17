package com.example.demo.ovh.credential.service;

import com.example.demo.ovh.credential.model.Credential;
import com.example.demo.ovh.credential.service.model.CredentialCreateRequest;

public interface ICredentialService {

    boolean existsAny();

    String getAnsibleCredentialSshKeyId();

    Credential handleSshKeyCreate(CredentialCreateRequest request);
}
