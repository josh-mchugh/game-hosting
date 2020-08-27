package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;

public interface IAwxCredentialService {

    boolean existsAny();

    AwxCredential getByName(String name);

    AwxCredential handleAwxCredentialCreate(AwxCredentialCreateRequest request);
}
