package com.example.demo.awx.credential.service;

import com.example.demo.awx.credential.entity.model.AwxCredential;
import com.example.demo.awx.credential.service.model.AwxCredentialCreateRequest;

public interface AwxCredentialService {

    AwxCredential handleCreated(AwxCredentialCreateRequest event);
}
