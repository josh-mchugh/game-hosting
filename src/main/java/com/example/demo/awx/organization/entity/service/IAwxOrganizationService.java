package com.example.demo.awx.organization.entity.service;

import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;

public interface IAwxOrganizationService {

    AwxOrganization handleCreated(AwxOrganizationCreatedEvent event);
}
