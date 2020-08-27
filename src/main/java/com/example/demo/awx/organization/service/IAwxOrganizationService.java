package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.model.AwxOrganization;
import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;

public interface IAwxOrganizationService {

    boolean existsAny();

    AwxOrganization handleOrganizationCreate(AwxOrganizationCreateRequest request);
}
