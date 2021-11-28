package com.example.demo.awx.host.feign;

import com.example.demo.awx.host.feign.model.HostApi;
import com.example.demo.awx.host.feign.model.HostCreateApi;
import com.example.demo.awx.host.feign.model.HostPatchApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HostFeignServiceImpl implements HostFeignService {

    private final HostClient hostClient;

    @Override
    public HostApi createHost(HostCreateApi body) {

        return hostClient.createHost(body);
    }

    @Override
    public HostApi updateHost(Long id, HostPatchApi body) {

        return hostClient.updateHost(id, body);
    }
}
