package com.example.demo.ovh.credential.feign;

import com.example.demo.ovh.credential.feign.model.SshKeyApi;
import com.example.demo.ovh.credential.feign.model.SshKeyCreateApi;

import java.util.List;

public interface SshKeyFeignService {

    List<SshKeyApi> getSshKeys();

    SshKeyApi createSshKey(SshKeyCreateApi body);
}
