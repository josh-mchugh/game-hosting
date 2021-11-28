package com.example.demo.ovh.credential.feign;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "sshKeyClient", url = "${ovh.base-url}", configuration = FeignOvhConfig.class)
public interface SshKeyClient {

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey")
    List<SshKeyApi> getSshKeys(@PathVariable("projectId") String projectId);

    @PostMapping("/1.0/cloud/project/{projectId}/sshkey")
    SshKeyApi createSshKey(@PathVariable("projectId") String projectId, @RequestBody SshKeyCreateApi body);
}
