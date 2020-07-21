package com.example.demo.ovh;

import com.example.demo.framework.feign.FeignAuthConfig;
import com.example.demo.ovh.model.Flavor;
import com.example.demo.ovh.model.Group;
import com.example.demo.ovh.model.GroupCreate;
import com.example.demo.ovh.model.Image;
import com.example.demo.ovh.model.Instance;
import com.example.demo.ovh.model.InstanceCreate;
import com.example.demo.ovh.model.Project;
import com.example.demo.ovh.model.SshKey;
import com.example.demo.ovh.model.SshKeyCreate;
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
    Project getProjectDetails(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/image")
    List<Image> getImages(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/image/{imageId}")
    Image getImage(@PathVariable("projectId") String projectId, @PathVariable("imageId") String imageId);

    @GetMapping("/1.0/cloud/project/{projectId}/flavor")
    List<Flavor> getFlavors(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/flavor/{flavorId}")
    Flavor getFlavorById(@PathVariable("projectId") String projectId, @PathVariable("flavorId") String flavorId);

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey")
    List<SshKey> getSshKeys(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    SshKey getSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);

    @PostMapping("/1.0/cloud/project/{projectId}/sshkey")
    SshKey createSshKey(@PathVariable("projectId") String projectId, @RequestBody SshKeyCreate body);

    @DeleteMapping("/1.0/cloud/project/{projectId}/sshkey/{sshKeyId}")
    void deleteSshKeyById(@PathVariable("projectId") String projectId, @PathVariable("sshKeyId") String sshKeyId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance/group")
    Group createGroup(@PathVariable("projectId") String projectId, @RequestBody GroupCreate body);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/group")
    List<Group> getGroups(@PathVariable("projectId") String projectId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/group/{groupId}")
    Group getGroupById(@PathVariable("projectId") String projectId, @PathVariable("groupId") String groupId);

    @DeleteMapping("/1.0/cloud/project/{projectId}/instance/group/{groupId}")
    void deleteGroupById(@PathVariable("projectId") String projectId, @PathVariable("groupId") String groupId);

    @GetMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}")
    Instance getInstanceById(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);

    @PostMapping("/1.0/cloud/project/{projectId}/instance")
    Instance createInstance(@PathVariable("projectId") String projectId, @RequestBody InstanceCreate body);

    @DeleteMapping("/1.0/cloud/project/{projectId}/instance/{instanceId}")
    void deleteInstance(@PathVariable("projectId") String projectId, @PathVariable("instanceId") String instanceId);
}
