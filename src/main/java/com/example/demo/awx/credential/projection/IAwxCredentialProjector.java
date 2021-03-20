package com.example.demo.awx.credential.projection;

import com.example.demo.awx.credential.entity.model.AwxCredential;

public interface IAwxCredentialProjector {

    AwxCredential getByName(String name);
}
