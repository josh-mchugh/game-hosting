package com.example.demo.ovh.instance.feign;

import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;

import java.util.List;

public interface InstanceFeignService {

    List<InstanceApi> getInstances();

    InstanceApi getInstanceById(String instanceId);

    InstanceApi createInstance( InstanceCreateApi body);

    void startInstance(String instanceId);

    void stopInstance(String instanceId);
}
