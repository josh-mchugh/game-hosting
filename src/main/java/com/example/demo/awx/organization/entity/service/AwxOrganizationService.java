package com.example.demo.awx.organization.entity.service;

import com.example.demo.awx.organization.aggregate.event.AwxOrganizationCreatedEvent;
import com.example.demo.awx.organization.entity.model.AwxOrganization;

public interface AwxOrganizationService {

    AwxOrganization handleCreated(AwxOrganizationCreatedEvent event);
}
