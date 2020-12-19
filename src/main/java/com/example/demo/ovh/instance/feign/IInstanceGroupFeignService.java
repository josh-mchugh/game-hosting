package com.example.demo.ovh.instance.feign;

import com.example.demo.ovh.instance.feign.model.InstanceGroupApi;
import com.example.demo.ovh.instance.feign.model.InstanceGroupCreateApi;

import java.util.List;

public interface IInstanceGroupFeignService {

    List<InstanceGroupApi> getInstanceGroups();

    InstanceGroupApi createInstanceGroup(InstanceGroupCreateApi body);

    void deleteInstanceGroupById(String groupId);
}
