package com.example.demo.framework.seed.awx.organization.projection;

import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationQuery;
import com.example.demo.framework.seed.awx.organization.projection.model.ExistsAnyAwxOrganizationResponse;

public interface IAwxOrganizationSeedProjectionService {

    ExistsAnyAwxOrganizationResponse existsAny(ExistsAnyAwxOrganizationQuery query);
}
