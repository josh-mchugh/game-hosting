package com.example.demo.ovh.instance.feign;

import com.example.demo.framework.feign.FeignOvhConfig;
import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "instanceGroupClient", url = "${ovh.base-url}", configuration = FeignOvhConfig.class)
public interface InstanceGroupClient {

    @PostMapping("/1.0/cloud/project/{projectId}/instance/group")
    InstanceGroupApi createInstanceGroup(@PathVariable("projectId") String projectId, @RequestBody InstanceGroupCreateApi body);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/group")
    List<InstanceGroupApi> getInstanceGroups(@PathVariable("projectId") String projectId);

    @DeleteMapping("/1.0/cloud/project/{projectId}/instance/group/{groupId}")
    void deleteInstanceGroupById(@PathVariable("projectId") String projectId, @PathVariable("groupId") String groupId);
}
