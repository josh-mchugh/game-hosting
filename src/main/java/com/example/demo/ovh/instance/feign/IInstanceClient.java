package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceApi;
import com.example.demo.ovh.instance.feign.model.InstanceCreateApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "instanceClient", url = "${ovh.base-url}", configuration = FeignOvhConfig.class)
public interface IInstanceClient {

    @GetMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}")
    InstanceApi getInstanceById(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance")
    List<InstanceApi> getInstances(@PathVariable("projectId") String projectId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance")
    InstanceApi createInstance(@PathVariable("projectId") String projectId, @RequestBody InstanceCreateApi body);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}/start")
    void startInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}/stop")
    void stopInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);
}
