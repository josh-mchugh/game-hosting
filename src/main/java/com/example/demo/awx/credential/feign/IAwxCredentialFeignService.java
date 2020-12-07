package com.example.demo.awx.credential.feign;

import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.awx.feign.ListResponse;

public interface IAwxCredentialFeignService {

    ListResponse<AwxCredentialApi> getCredentials();

    AwxCredentialApi createCredential(AwxCredentialCreateApi body);
}
