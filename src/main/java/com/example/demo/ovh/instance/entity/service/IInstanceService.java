package com.example.demo.ovh.instance.entity.service;

import com.example.demo.ovh.instance.aggregate.event.InstanceCreatedEvent;
import com.example.demo.ovh.instance.aggregate.event.InstanceUpdatedEvent;
import com.example.demo.ovh.instance.entity.model.Instance;

public interface IInstanceService {

    Instance handleCreated(InstanceCreatedEvent event);

    Instance handleUpdated(InstanceUpdatedEvent event);
}
