package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.model.InstanceGroup;
import com.example.demo.ovh.instance.service.model.InstanceGroupCreateRequest;

public interface IInstanceGroupService {

    InstanceGroup handleInstanceGroupCreate(InstanceGroupCreateRequest request);
}
