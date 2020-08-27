package com.example.demo.ovh.feign.ssh;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.feign.ssh.model.SshKeyApi;
import com.example.demo.ovh.feign.ssh.model.SshKeyCreateApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "sshKeyClient", url = "${app.ovh.base-url}", configuration = FeignOvhConfig.class)
public interface SshKeyClient {

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey")
    List<SshKeyApi> getSshKeys(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    SshKeyApi getSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);

    @PostMapping("/1.0/cloud/project/{projectId}/sshkey")
    SshKeyApi createSshKey(@PathVariable("projectId") String projectId, @RequestBody SshKeyCreateApi body);

    @DeleteMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    void deleteSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);
}
