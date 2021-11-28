package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstanceFeignServiceImpl implements InstanceFeignService {

    private final OvhConfig ovhConfig;
    private final InstanceClient instanceClient;

    @Override
    public List<InstanceApi> getInstances() {

        return instanceClient.getInstances(ovhConfig.getProjectId());
    }

    @Override
    public InstanceApi getInstanceById(String instanceId) {

        return instanceClient.getInstanceById(ovhConfig.getProjectId(), instanceId);
    }

    @Override
    public InstanceApi createInstance(InstanceCreateApi body) {

        return instanceClient.createInstance(ovhConfig.getProjectId(), body);
    }

    @Override
    public void startInstance(String instanceId) {

        instanceClient.startInstance(ovhConfig.getProjectId(), instanceId);
    }

    @Override
    public void stopInstance(String instanceId) {

        instanceClient.stopInstance(ovhConfig.getProjectId(), instanceId);
    }
}
