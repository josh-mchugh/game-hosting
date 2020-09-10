package com.example.demo.awx.host.service;

import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;

public interface IAwxHostService {

    boolean existsAny();

    AwxHost handleCreateRequest(AwxHostCreateRequest request);

    AwxHost handleEnabledRequest(Long hostId);

    AwxHost handleDisableRequest(Long hostId);
}
