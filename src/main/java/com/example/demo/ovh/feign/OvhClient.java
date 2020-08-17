package com.example.demo.ovh.feign;

import com.example.demo.framework.feign.FeignAuthConfig;
import com.example.demo.ovh.feign.model.OvhAvailableRegionApiResponse;
import com.example.demo.ovh.feign.model.OvhFlavorApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceGroupApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceGroupCreateApiRequest;
import com.example.demo.ovh.feign.model.OvhImageApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceApiResponse;
import com.example.demo.ovh.feign.model.OvhInstanceCreateApiRequest;
import com.example.demo.ovh.feign.model.OvhProjectApiResponse;
import com.example.demo.ovh.feign.model.OvhRegionApiResponse;
import com.example.demo.ovh.feign.model.OvhSshKeyApiResponse;
import com.example.demo.ovh.feign.model.OvhSshKeyCreateApiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "${app.ovh.base-url}", configuration = FeignAuthConfig.class)
public interface OvhClient {

    @GetMapping("/1.0/cloud/project/{projectId}")
    OvhProjectApiResponse getProjectDetails(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/region")
    List<String> getRegions(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/region/{regionId}")
    OvhRegionApiResponse getRegion(@PathVariable("projectId") String projectId, @PathVariable("regionId") String regionId);

    @GetMapping("1.0/cloud/project/{projectId}/regionAvailable")
    List<OvhAvailableRegionApiResponse> getAvailableRegions(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/image")
    List<OvhImageApiResponse> getImages(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/image/{imageId}")
    OvhImageApiResponse getImage(@PathVariable("projectId") String projectId, @PathVariable("imageId") String imageId);

    @GetMapping("/1.0/cloud/project/{projectId}/flavor")
    List<OvhFlavorApiResponse> getFlavors(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/flavor/{flavorId}")
    OvhFlavorApiResponse getFlavorById(@PathVariable("projectId") String projectId, @PathVariable("flavorId") String flavorId);

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey")
    List<OvhSshKeyApiResponse> getSshKeys(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    OvhSshKeyApiResponse getSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);

    @PostMapping("/1.0/cloud/project/{projectId}/sshkey")
    OvhSshKeyApiResponse createSshKey(@PathVariable("projectId") String projectId, @RequestBody OvhSshKeyCreateApiRequest body);

    @DeleteMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    void deleteSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/group")
    OvhInstanceGroupApiResponse createGroup(@PathVariable("projectId") String projectId, @RequestBody OvhInstanceGroupCreateApiRequest body);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/group")
    List<OvhInstanceGroupApiResponse> getGroups(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/group/{groupId}")
    OvhInstanceGroupApiResponse getGroupById(@PathVariable("projectId") String projectId, @PathVariable("groupId") String groupId);

    @DeleteMapping("/1.0/cloud/project/{projectId}/instance/group/{groupId}")
    void deleteGroupById(@PathVariable("projectId") String projectId, @PathVariable("groupId") String groupId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}")
    OvhInstanceApiResponse getInstanceById(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance")
    List<OvhInstanceApiResponse> getInstances(@PathVariable("projectId") String projectId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance")
    OvhInstanceApiResponse createInstance(@PathVariable("projectId") String projectId, @RequestBody OvhInstanceCreateApiRequest body);

    @DeleteMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}")
    void deleteInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}/start")
    void startInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}/stop")
    void stopInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);
}
