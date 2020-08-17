package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.service.model.InstanceCreateRequest;
import com.example.demo.ovh.instance.service.model.InstanceUpdateRequest;

public interface IInstanceService {

    Instance handleInstanceCreate(InstanceCreateRequest request);

    Instance handleInstanceUpdate(InstanceUpdateRequest request);
}
