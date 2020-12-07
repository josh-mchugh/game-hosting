package com.example.demo.awx.organization.projection;

import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdQuery;
import com.example.demo.awx.organization.projection.model.FetchAwxOrganizationIdByAwxIdResponse;

public interface IAwxOrganizationProjection {

    boolean existsAny();

    FetchAwxOrganizationIdByAwxIdResponse fetchAwxOrganizationIdByAwxId(FetchAwxOrganizationIdByAwxIdQuery query);
}
