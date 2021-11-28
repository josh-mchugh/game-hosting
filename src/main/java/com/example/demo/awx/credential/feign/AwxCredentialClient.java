package com.example.demo.awx.credential.feign;

import com.example.demo.awx.credential.feign.model.AwxCredentialApi;
import com.example.demo.awx.credential.feign.model.AwxCredentialCreateApi;
import com.example.demo.awx.feign.ListResponse;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "credentialClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface AwxCredentialClient {

    @GetMapping("/api/v2/organizations/{organizationId}/credentials/")
    ListResponse<AwxCredentialApi> getCredentials(@PathVariable("organizationId") Long organizationId);

    @PostMapping("/api/v2/organizations/{organizationId}/credentials/")
    AwxCredentialApi createCredential(@PathVariable("organizationId") Long organizationId, @RequestBody AwxCredentialCreateApi body);
}
