package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InstanceGroupFeignServiceImpl implements InstanceGroupFeignService {

    private final OvhConfig ovhConfig;
    private final InstanceGroupClient instanceGroupClient;

    @Override
    public List<InstanceGroupApi> getInstanceGroups() {

        return instanceGroupClient.getInstanceGroups(ovhConfig.getProjectId());
    }

    @Override
    public InstanceGroupApi createInstanceGroup(InstanceGroupCreateApi body) {

        return instanceGroupClient.createInstanceGroup(ovhConfig.getProjectId(), body);
    }

    @Override
    public void deleteInstanceGroupById(String groupId) {

        instanceGroupClient.deleteInstanceGroupById(ovhConfig.getProjectId(), groupId);
    }
}
