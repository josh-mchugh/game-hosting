package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.host.service.model.AwxHostDisableRequest;
import com.example.demo.awx.host.service.model.AwxHostEnableRequest;

public interface AwxHostService {

    AwxHost handleCreate(AwxHostCreateRequest request);

    AwxHost handleEnable(AwxHostEnableRequest request);

    AwxHost handleDisable(AwxHostDisableRequest request);
}
