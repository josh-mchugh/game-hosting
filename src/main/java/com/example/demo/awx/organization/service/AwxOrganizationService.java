package com.example.demo.awx.organization.service;

import com.example.demo.awx.organization.service.model.AwxOrganizationCreateRequest;
import com.example.demo.awx.organization.entity.model.AwxOrganization;

public interface AwxOrganizationService {

    AwxOrganization handleCreate(AwxOrganizationCreateRequest event);
}
