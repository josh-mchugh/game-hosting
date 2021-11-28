package com.example.demo.ovh.credential.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SshKeyFeignServiceImpl implements SshKeyFeignService {

    private final OvhConfig ovhConfig;
    private final SshKeyClient sshKeyClient;

    @Override
    public List<SshKeyApi> getSshKeys() {

        return sshKeyClient.getSshKeys(ovhConfig.getProjectId());
    }

    @Override
    public SshKeyApi createSshKey(SshKeyCreateApi body) {

        return sshKeyClient.createSshKey(ovhConfig.getProjectId(), body);
    }
}
