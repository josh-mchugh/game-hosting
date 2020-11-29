package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceGroupCreatedEvent;
import com.example.demo.ovh.instance.entity.model.InstanceGroup;

public interface IInstanceGroupService {

    InstanceGroup handleCreated(InstanceGroupCreatedEvent event);
}
