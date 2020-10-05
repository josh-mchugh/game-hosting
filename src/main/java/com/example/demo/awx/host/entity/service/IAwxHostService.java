package com.example.demo.awx.host.entity.service;

import com.example.demo.awx.host.aggregate.event.AwxHostCreatedEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostDisabledEvent;
import com.example.demo.awx.host.aggregate.event.AwxHostEnabledEvent;
import com.example.demo.awx.host.entity.model.AwxHost;

public interface IAwxHostService {

    AwxHost handleCreated(AwxHostCreatedEvent event);

    AwxHost handleEnabled(AwxHostEnabledEvent event);

    AwxHost handleDisabled(AwxHostDisabledEvent event);
}
