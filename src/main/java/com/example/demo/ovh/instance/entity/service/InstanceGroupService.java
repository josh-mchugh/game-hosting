package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;

public interface InstanceGroupService {

    InstanceGroup handleCreated(InstanceGroupCreatedEvent event);
}
