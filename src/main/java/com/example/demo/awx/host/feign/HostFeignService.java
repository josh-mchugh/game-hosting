package com.example.demo.awx.host.feign;

import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.feign.model.HostCreateApi;
import com.example.demo.awx.host.feign.model.HostPatchApi;

public interface HostFeignService {

    HostApi createHost(HostCreateApi body);

    HostApi updateHost(Long id, HostPatchApi body);
}
