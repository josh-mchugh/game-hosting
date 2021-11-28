package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectQuery;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxOrganizationIdByAwxIdResponse;

public interface AwxProjectSeedProjectionService {

    ExistsAnyAwxProjectResponse existsAny(ExistsAnyAwxProjectQuery query);

    FetchAwxCredentialByNameResponse fetchAwxCredentialIdByName(FetchAwxCredentialByNameQuery query);

    FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query);
}
